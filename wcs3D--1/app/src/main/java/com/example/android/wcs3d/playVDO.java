package com.example.android.wcs3d;

/**
 * Created by android on 2/24/2015.
 */


import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class playVDO extends Activity{

    String TAG = "list";
    String th101="http://www.caiproject.com/subjects/thai/season3/101/video/thai101ad.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vdo);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);



       final  VideoView video1=(VideoView)findViewById(R.id.videoView1);
        video1.setVideoPath(th101);
        MediaController mediaController=new MediaController(this);
        mediaController.setAnchorView(video1);
        video1.setMediaController(mediaController);
        video1.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){

            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.i(TAG, "Duration = " +  video1.getDuration());
            }
        });

        video1.start();
       // finish(); //comment
    }
}
