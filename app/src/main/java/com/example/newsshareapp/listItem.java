package com.example.newsshareapp;

public class listItem {
    private String head;
    private String desc;
    private String url;
    private String openurl;
    public listItem(String head,String desc,String url,String openurl)
    {
        this.head=head;
        this.desc=desc;
        this.url=url;
        this.openurl=openurl;
    }


    public String getHead()
    {
        return head;
    }
    public String getDesc()
    {
        return desc;
    }
    public String getUrl()
    {
        return url;
    }
    public String getOpenurl(){return openurl;}
}
