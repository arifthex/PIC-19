package com.example.picovid_19.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.picovid_19.R;

public class SplashScreen extends AppCompatActivity {
    //waktu tunggu 3 detik
    private int loading = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent home = new Intent(SplashScreen.this, HomeScreen.class);
                startActivity(home);
                finish();
            }
        }, loading);
    }
}
