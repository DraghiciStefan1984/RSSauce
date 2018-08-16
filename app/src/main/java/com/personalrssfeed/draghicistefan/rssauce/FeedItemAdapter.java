package com.personalrssfeed.draghicistefan.rssauce;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Draghici Stefan on 10.01.2016.
 */
public class FeedItemAdapter extends ArrayAdapter<RssItem>
{
    public FeedItemAdapter(Context context, ArrayList<RssItem> items)
    {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        RssItem rssItem=getItem(position);
        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.rss_feed_item_row, parent, false);
        }
        TextView description= (TextView) convertView.findViewById(R.id.rssFeedItemDescriptionTextView);
        TextView title= (TextView) convertView.findViewById(R.id.rssFeedItemTitleTextView);
        description.setText(rssItem.getDescription());
        title.setText(rssItem.getTitle());

        return convertView;
    }
}
