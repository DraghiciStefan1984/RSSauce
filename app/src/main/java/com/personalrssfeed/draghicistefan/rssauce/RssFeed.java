package com.personalrssfeed.draghicistefan.rssauce;

/**
 * Created by Draghici Stefan on 09.01.2016.
 */
public class RssFeed
{
    public int id;
    public String rssFeedTitle, rssFeedAddress;

    public RssFeed(String rssFeedTitle, String rssFeedAddress)
    {
        this.rssFeedTitle = rssFeedTitle;
        this.rssFeedAddress = rssFeedAddress;
    }

    public RssFeed(int id, String rssFeedTitle, String rssFeedAddress)
    {
        this.id = id;
        this.rssFeedTitle = rssFeedTitle;
        this.rssFeedAddress = rssFeedAddress;
    }
}
