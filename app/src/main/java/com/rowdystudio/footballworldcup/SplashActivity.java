package com.rowdystudio.footballworldcup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import com.crisisstudio.trial1toastlibrary.toastmaker;

public class SplashActivity extends AppCompatActivity {

    ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        // statusbar color
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(ContextCompat.getColor(SplashActivity.this, R.color.orange));

        // support actionbar hide
        getSupportActionBar().hide();


        progressBar = findViewById(R.id.progressBar);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();


                if ( networkInfo!=null &&  networkInfo.isConnected() ){

                    progressBar.setVisibility(View.GONE);
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    overridePendingTransition(R.anim.anim1, R.anim.anim2);
                    finish();

                } else {

                    progressBar.setVisibility(View.GONE);
                    toastmaker.newtoast(SplashActivity.this, "No Internet");

                }



            }
        },2500);


    }
}