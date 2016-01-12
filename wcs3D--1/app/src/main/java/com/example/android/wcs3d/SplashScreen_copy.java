package com.example.android.wcs3d;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class SplashScreen_copy extends Activity {

	// Splash screen timer
	//private static int SPLASH_TIME_OUT = 2000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_login);



        //setContentView(R.layout.activity_splash);  //original

        /*
		new Handler().postDelayed(new Runnable() {



			@Override
			public void run() {

                  Intent i = new Intent(SplashScreen_copy.this, MainActivity.class);
                startActivity(i);
                finish();
			}
		}, SPLASH_TIME_OUT);
        */


	}


}
