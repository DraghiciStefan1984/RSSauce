package com.personalrssfeed.draghicistefan.rssauce;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import at.theengine.android.simple_rss2_android.RSSItem;
import at.theengine.android.simple_rss2_android.SimpleRss2Parser;
import at.theengine.android.simple_rss2_android.SimpleRss2ParserCallback;

public class MainActivity extends AppCompatActivity
{
    ArrayList<RssItem> rssItems;
    List<RssFeed> rssFeeds;
    int feedCount;
    int retreivedFeedCount;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, AddFeedActivity.class);
                startActivity(intent);
            }
        });

        rssItems = new ArrayList<>();
        rssFeeds = new Database(this).getRssFeeds();
        feedCount = rssFeeds.size();
        retreivedFeedCount = 0;


        for (int i = 0; i < rssFeeds.size(); i++)
        {
            getFeedItems(rssFeeds.get(i).rssFeedAddress);
        }
    }

    private void getFeedItems(String feedAddress)
    {
        try
        {
            SimpleRss2Parser parser = new SimpleRss2Parser(feedAddress,
                    new SimpleRss2ParserCallback()
                    {
                        @Override
                        public void onFeedParsed(List<RSSItem> items)
                        {
                            for (int i = 0; i < items.size(); i++)
                            {
                                RssItem item = new RssItem();
                                item.setTitle(items.get(i).getTitle());
                                item.setDescription(items.get(i).getDescription());
                                item.setLink(items.get(i).getLink());
                                rssItems.add(item);
                            }
                            populateListView();

                        }

                        @Override
                        public void onError(Exception ex)
                        {
                            populateListView();
                        }
                    });
            parser.parseAsync();
        } catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
            populateListView();
        }

    }

    public void populateListView()
    {
        retreivedFeedCount++;
        if(retreivedFeedCount==feedCount)
        {
            Log.d("Feeds retreived", "got all feeds");
            listView= (ListView) findViewById(R.id.rssFeedItemListView);
            listView.setAdapter(new FeedItemAdapter(this, rssItems));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    Intent intent=new Intent(MainActivity.this, RssItemViewActivity.class);
                    RssItem item=rssItems.get(position);
                    intent.putExtra("url", item.getLink().toString());
                    intent.putExtra("title", item.getTitle().toString());
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            Intent editIntent = new Intent(MainActivity.this, EditRssFeedActivity.class);
            startActivity(editIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
