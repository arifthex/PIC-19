package com.example.picovid_19.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.picovid_19.Model.NewsModel;
import com.example.picovid_19.R;

import java.util.List;


public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.MyViewHolder> {

    private List<NewsModel> newsModels;
    private Context context;

    public NewsListAdapter(Context context, List<NewsModel> newsModels) {
        this.newsModels = newsModels;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout rl_layout;
        public TextView tv_title, tv_email, tv_desk;

        public MyViewHolder(View view) {
            super(view);
            rl_layout = view.findViewById(R.id.rl_layout);
            tv_title = view.findViewById(R.id.tv_title);
            tv_email = view.findViewById(R.id.tv_email);
            tv_desk = view.findViewById(R.id.tv_Deskripsi);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item_holder, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
       NewsModel newsModel = newsModels.get(position);
       holder.tv_title.setText(newsModel.getNama());
       holder.tv_email.setText(newsModel.getEmail());
       holder.tv_desk.setText(newsModel.getDesk());
    }


    @Override
    public int getItemCount() {
        return newsModels.size();
    }

}
