package com.example.picovid_19.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;

import com.example.picovid_19.Adapter.NewsListAdapter;
import com.example.picovid_19.Model.NewsModel;
import com.example.picovid_19.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class News_Activity extends AppCompatActivity {
    //Variabel untuk mengatur Date
    TextView txtdate;
    String Today;

    private DatabaseReference database;
    private ProgressDialog loading;

    private List<NewsModel> newsModels = new ArrayList<>();
    private NewsListAdapter newsListAdapter;

    //Untuk mengambil Id pada activity_news
    private RecyclerView list_news;
    private FloatingActionButton float_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        //Insialisasi Date
        txtdate = findViewById(R.id.txtDate);
        Date dateNow = Calendar.getInstance().getTime();
        Today = (String) DateFormat.format("EEEE", dateNow);

        //Method mengambil date
        getdate();

        //mengambil Id layout
        list_news = findViewById(R.id.rc_list);
        float_add = findViewById(R.id.float_btn);

        //Mengambil class neswlistadapter
        newsListAdapter = new NewsListAdapter(this, newsModels);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        list_news.setLayoutManager(mLayoutManager);
        list_news.setItemAnimator(new DefaultItemAnimator());
        list_news.setAdapter(newsListAdapter);

        float_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(News_Activity.this, AddActivity.class));
            }
        });

        //Membaca data firebase dengan reference news
        database = FirebaseDatabase.getInstance().getReference("news");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                newsModels.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    NewsModel newsModel = postSnapshot.getValue(NewsModel.class);
                    newsModel.setKey(postSnapshot.getKey());
                    newsModels.add(newsModel);
                }
                newsListAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    //Method tanggal
    private void getdate() {
        Date date = Calendar.getInstance().getTime();
        String tanggal = (String) DateFormat.format("d MMMM yyyy", date);
        String format = Today + ", " + tanggal;
        txtdate.setText(format);
    }
}
