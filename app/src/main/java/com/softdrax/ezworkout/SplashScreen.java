package com.softdrax.ezworkout;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(() -> {

            //    if user is already logged in
            GoogleSignInAccount gsa = GoogleSignIn.getLastSignedInAccount(SplashScreen.this);
            Intent iHome;
            if (gsa != null) {
                iHome = new Intent(SplashScreen.this, MainActivity.class);
            } else {

                //if user is logged out
                iHome = new Intent(SplashScreen.this, LoginActivity.class);
            }
            Bundle b = ActivityOptions.makeSceneTransitionAnimation(SplashScreen.this).toBundle();
            startActivity(iHome, b);
            finish();
        }, 2000);

    }
}