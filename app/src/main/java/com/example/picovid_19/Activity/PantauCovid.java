package com.example.picovid_19.Activity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.picovid_19.Fragment.IndonesiaFragment;
import com.example.picovid_19.Fragment.GlobalFragment;
import com.example.picovid_19.Fragment.RiwayatFragment;
import com.example.picovid_19.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;
import java.util.Date;

/**
 Arif Wicaksono
 14116033
 */

public class PantauCovid extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    TextView txtdate;
    String Today;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.covid_pantau);

        //Insialiasasi Fragment global di awal agar muncul pertama dengan menimpa ID Layout fragment
        if (savedInstanceState == null) {
            GlobalFragment globalFragment = new GlobalFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.flMain, globalFragment)
                    .commit();
        }

        //instialiasai Date
        txtdate = findViewById(R.id.txtDate);
        Date dateNow = Calendar.getInstance().getTime();
        Today = (String) DateFormat.format("EEEE", dateNow);

        //Mengambil Id bootom nav dan aksi yang diberikan
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        getDate();
    }

    private void getDate() {
        Date date = Calendar.getInstance().getTime();
        String tanggal = (String) DateFormat.format("d MMMM yyyy", date);
        String format = Today + ", " + tanggal;
        txtdate.setText(format);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //Percabangan aski yang diberikan menu Bottom ketika di pilih
        switch (item.getItemId()) {

            case R.id.summaryMenu:
                GlobalFragment globalFragment = new GlobalFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flMain, globalFragment)
                        .commit();
                return true;

            case R.id.summaryIdnMenu:
                IndonesiaFragment indonesiaFragment = new IndonesiaFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flMain, indonesiaFragment)
                        .commit();
                return true;

            case R.id.historyMenu:
                RiwayatFragment riwayatFragment = new RiwayatFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flMain, riwayatFragment)
                        .commit();
                return true;
        }
        return false;
    }

}
