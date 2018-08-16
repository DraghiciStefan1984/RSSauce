package com.personalrssfeed.draghicistefan.rssauce;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddFeedActivity extends AppCompatActivity
{
    private Button saveButton;
    private EditText feedAddress, feedName;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        saveButton= (Button) findViewById(R.id.addFeedButton);
        feedAddress= (EditText) findViewById(R.id.addFeeEditText);
        feedName= (EditText) findViewById(R.id.addFeedNameEditText);

        saveButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(feedAddress.getText().length()<6)
                {
                    Toast.makeText(getApplicationContext(), "Address is too short", Toast.LENGTH_LONG).show();
                    return;
                }
                Database db=new Database(AddFeedActivity.this);
                RssFeed feed=new RssFeed(feedName.getText().toString(), feedAddress.getText().toString());
                db.addRssFeed(feed);
                Toast.makeText(getApplicationContext(), "Rss Feed Successfully Added!", Toast.LENGTH_LONG).show();
                feedName.setText("");
                feedAddress.setText("");
            }
        });
    }
}
