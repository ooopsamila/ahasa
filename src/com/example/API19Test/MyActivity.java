package com.example.API19Test;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MyActivity extends TabActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TabHost tabHost = getTabHost();

        // Tab for News
        TabSpec newsSpec = tabHost.newTabSpec("News");
        // setting Title and Icon for the Tab
        newsSpec.setIndicator("News", getResources().getDrawable(R.drawable.icon_photos_tab));
        Intent newsIntent = new Intent(this, NewsActivity.class);
        newsSpec.setContent(newsIntent);


        // Tab for Videos
        TabSpec videoSpec = tabHost.newTabSpec("Videos");
        videoSpec.setIndicator("Videos", getResources().getDrawable(R.drawable.icon_videos_tab));
        Intent videosIntent = new Intent(this, VideoActivity.class);
        videoSpec.setContent(videosIntent);

        // Adding all TabSpec to TabHost
        tabHost.addTab(newsSpec); // Adding News tab
        tabHost.addTab(videoSpec); // Adding videos tab

    }
}
