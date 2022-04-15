package com.example.newsshareapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.browser.customtabs.CustomTabsIntent;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<listItem> arrayList;
    private Context context;
   

    public MyAdapter(List<listItem> arrayList,Context context)
    {
        this.arrayList=arrayList;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
       View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text_row_item,viewGroup,false);

       return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
      listItem listitem=arrayList.get(position);
        viewHolder.textViewhead.setText(listitem.getHead());
        viewHolder.textViewdesc.setText(listitem.getDesc());
        Glide.with(context).load(listitem.getUrl()).into(viewHolder.imageView);

       



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }




    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewhead;
        public TextView textViewdesc;
        public ImageView imageView;



        public ViewHolder(View view) {
            super(view);
            textViewhead = (TextView) view.findViewById(R.id.heading);
            textViewdesc = (TextView) view.findViewById(R.id.textView);
            imageView=(ImageView) view.findViewById(R.id.imageVieew);
            


        }
    }
    interface NewsItemClicked{

    }
}

