package com.example.picovid_19.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.picovid_19.R;

/**
 Arif Wicaksono
 14116033
 */

public class HomeScreen extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_home);
    }

    //Pindah ke menu Pantau Covid-19
    public void mainActivity(View view) {
        Intent i = new Intent(HomeScreen.this, PantauCovid.class);
        startActivity(i);
    }

    //Pindah ke menu Lapor Covid-19
    public void News_Activity(View view) {
        Intent i = new Intent(HomeScreen.this, News_Activity.class);
        startActivity(i);
    }
}
