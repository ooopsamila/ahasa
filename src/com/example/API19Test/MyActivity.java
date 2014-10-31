package com.example.API19Test;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import static com.example.API19Test.NewsActivity.*;

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
        tabHost.addTab(newsSpec); // Adding Nes tab
        tabHost.addTab(videoSpec); // Adding videos tab

        getTabHost().setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            @Override
            public void onTabChanged(String tabId) {

                int tabIndex = getTabHost().getCurrentTab();
                Log.i("@@@@@@@@ ANN CLICK TAB NUMBER", "------" + tabIndex);

                if (tabIndex == 0) {

                }
                else if (tabIndex ==1) {
                    Log.i("@@@@@@@@@@ Inside onClick tab 1", "onClick tab");
                }

            }
        });
    }
}
