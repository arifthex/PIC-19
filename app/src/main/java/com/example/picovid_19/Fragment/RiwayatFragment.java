package com.example.picovid_19.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.picovid_19.Adapter.RiwayatListAdapter;
import com.example.picovid_19.Model.RiwayatModel;
import com.example.picovid_19.R;
import com.example.picovid_19.viewmodel.RiwayatViewModel;

import java.util.ArrayList;

public class RiwayatFragment extends Fragment {
    private RiwayatListAdapter adapter;
    private ProgressDialog mProgressApp;

    public RiwayatFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_riwayat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProgressApp = new ProgressDialog(getActivity());
        mProgressApp.setTitle("Mohon tunggu");
        mProgressApp.setCancelable(false);
        mProgressApp.setMessage("Sedang menampilkan data...");

        RecyclerView recyclerView = view.findViewById(R.id.listRecycler);
        adapter = new RiwayatListAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        loadListData();
    }

    private void loadListData() {
        RiwayatViewModel viewModel = new ViewModelProvider(this,
                new ViewModelProvider.NewInstanceFactory()).get(RiwayatViewModel.class);
        viewModel.setTodayData();
        mProgressApp.show();
        viewModel.getTodayListData().observe(this, new Observer<ArrayList<RiwayatModel>>() {
            @Override
            public void onChanged(ArrayList<RiwayatModel> riwayatModels) {
                adapter.setRiwayatModels(riwayatModels);
                mProgressApp.dismiss();
            }
        });
    }
}