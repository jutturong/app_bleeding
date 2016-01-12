package com.example.android.wcs3d;

/**
 * Created by android on 2/24/2015.
 */


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class thai105 extends Activity{

    String TAG = "list";
    String th="http://www.caiproject.com/subjects/thai/season3/105/video/thai105ad.mp4";
    private ProgressDialog progress = null;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vdo);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);



       final  VideoView video1=(VideoView)findViewById(R.id.videoView1);
        video1.setVideoPath(th);
        MediaController mediaController=new MediaController(this);
        mediaController.setAnchorView(video1);
        video1.setMediaController(mediaController);

       Toast.makeText( getBaseContext(),"รอสักครู่...", Toast.LENGTH_SHORT).show(); //text preloading..

        video1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.i(TAG, "Duration = " + video1.getDuration());
            }
        });

       video1.requestFocus();  //comment1


        video1.start();
       // finish(); //comment
    }
}
