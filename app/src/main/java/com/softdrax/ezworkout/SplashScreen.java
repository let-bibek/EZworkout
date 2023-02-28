package com.softdrax.ezworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent iHome = new Intent(SplashScreen.this, LoginActivity.class);
                Bundle b= ActivityOptions.makeSceneTransitionAnimation(SplashScreen.this).toBundle();
                startActivity(iHome,b);
                finish();
            }
        }, 2000);

    }
}