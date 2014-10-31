package com.example.API19Test;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends Activity {
    String SrcPath = "http://192.168.0.118/AndroidCommercial.3gp";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_layout);
        try {
            VideoView myVideoView = (VideoView) findViewById(R.id.myvideoview);
            myVideoView.setVideoURI(Uri.parse(SrcPath));
            myVideoView.setMediaController(new MediaController(this));
            myVideoView.requestFocus();
            myVideoView.start();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
