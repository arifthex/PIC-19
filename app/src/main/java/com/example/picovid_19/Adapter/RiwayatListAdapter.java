package com.example.picovid_19.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.picovid_19.Model.RiwayatModel;
import com.example.picovid_19.R;

import java.util.ArrayList;

public class RiwayatListAdapter extends RecyclerView.Adapter<RiwayatListAdapter.ViewHolder> {

    //Menampung class RiwayatModel sebagai arrayList
    private ArrayList<RiwayatModel> riwayatModels = new ArrayList<>();
    private Context context;

    public RiwayatListAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<RiwayatModel> getRiwayatModels() {
        return riwayatModels;
    }

    public void setRiwayatModels(ArrayList<RiwayatModel> items) {
        if (riwayatModels != null) {
            if (riwayatModels.size() > 0) {
                riwayatModels.clear();
            }
            riwayatModels.addAll(items);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Insialisasi layout List ke riwayat item holder
        View view = LayoutInflater.from(context).inflate(R.layout.riwayat_item_holder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Mengambil elemen dari dataset (ArrayList) pada posisi tertentu
        // Mengeset isi view dengan elemen dari dataset tersebut
        holder.tvConfirmed.setText(riwayatModels.get(position).getConfirmed());
        holder.tvRecovered.setText(riwayatModels.get(position).getRecovered());
        holder.tvDeath.setText(riwayatModels.get(position).getDeaths());
        holder.tvListCountry.setText("Lokasi : " + riwayatModels.get(position).getCountryRegion());
    }

    @Override
    public int getItemCount() {
        //Mengembalikan jumlah semua data yang dipegang adapter
        return riwayatModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvConfirmed;
        TextView tvRecovered;
        TextView tvDeath;
        TextView tvListCountry;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            //mengambil ID dari layout item holder
            tvConfirmed = itemView.findViewById(R.id.txtKonfirm);
            tvRecovered = itemView.findViewById(R.id.txtSembuh);
            tvDeath = itemView.findViewById(R.id.txtDeath);
            tvListCountry = itemView.findViewById(R.id.txtNegara);
        }
    }
}
