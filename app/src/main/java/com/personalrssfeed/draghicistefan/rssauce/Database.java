package com.personalrssfeed.draghicistefan.rssauce;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Draghici Stefan on 09.01.2016.
 */
public class Database extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="rssFeeds";
    private static final String TABLE_FFEDS="feeds";
    private static final String KEY_ID="id";
    private static final String KEY_FEED_TITLE="title";
    private static final String KEY_FEED_ADDRESS="address";

    public Database(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_FEEDS_TABLE="CREATE TABLE "+TABLE_FFEDS+"("+KEY_ID+" INTEGER PRIMARY KEY,"+KEY_FEED_TITLE+" TEXT,"+KEY_FEED_ADDRESS+" TEXT"+")";
        db.execSQL(CREATE_FEEDS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FFEDS);
        onCreate(db);
    }

    public void addRssFeed(RssFeed rssFeed)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_FEED_TITLE, rssFeed.rssFeedTitle);
        contentValues.put(KEY_FEED_ADDRESS, rssFeed.rssFeedAddress);
        db.insert(TABLE_FFEDS, null, contentValues);
        db.close();
    }

    public List<RssFeed> getRssFeeds()
    {
        List<RssFeed> results=new ArrayList<>();
        String selectQuery="SELECT * FROM "+TABLE_FFEDS;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst())
        {
            do
            {
                RssFeed rssFeed=new RssFeed(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
                results.add(rssFeed);
            }
            while (cursor.moveToNext());
        }
        db.close();
        return results;
    }

    public void deteleRssFeed(RssFeed rssFeed)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_FFEDS, KEY_ID+"=?", new String[]{String.valueOf(rssFeed.id)});
        db.close();
    }
}
