package com.personalrssfeed.draghicistefan.rssauce;

import java.net.URL;

/**
 * Created by Draghici Stefan on 09.01.2016.
 */
public class RssItem
{
    private String title, description;
    private URL link;

    public RssItem(){}

    public RssItem(String title, String description, URL link)
    {
        this.title = title;
        this.description = description;
        this.link = link;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public URL getLink()
    {
        return link;
    }

    public void setLink(URL link)
    {
        this.link = link;
    }
}
