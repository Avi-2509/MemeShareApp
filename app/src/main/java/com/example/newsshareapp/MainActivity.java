package com.example.newsshareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    private List<listItem> listitem;
    String clickUrl=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listitem = new ArrayList<>();
        loadRecyclerviewData();
    }

    public void loadRecyclerviewData() {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading News...");
        progressDialog.show();
        getSupportActionBar().hide();
        String url = "https://saurav.tech/NewsAPI/top-headlines/category/health/in.json";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();


                        try { 
                            JSONArray array= response.getJSONArray("articles");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                listItem list = new listItem(o.getString("title")
                                        , o.getString("description"),
                                        o.getString("urlToImage"),
                                        o.getString("url"));
//                                Button button=findViewById(R.id.button);
//                                button.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        Toast.makeText(MainActivity.this, "hello here", Toast.LENGTH_SHORT).show();
//                                    }
//                                });

                                listitem.add(list);

                            }
                            adapter = new MyAdapter(listitem,getApplicationContext());
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {


                    public void onErrorResponse(VolleyError error) {

                       progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "Something went wrong...", Toast.LENGTH_SHORT).show();

                    }
                });
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }




}
