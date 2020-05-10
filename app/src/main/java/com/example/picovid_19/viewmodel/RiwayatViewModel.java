package com.example.picovid_19.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.picovid_19.Model.RiwayatModel;
import com.example.picovid_19.api.ApiEndPoint;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RiwayatViewModel extends ViewModel {

    private MutableLiveData<ArrayList<RiwayatModel>> mutableLiveData = new MutableLiveData<>();

    public void setTodayData() {
        Retrofit retrofit = com.example.picovid_19.api.ApiService.getRetrofitService();
        ApiEndPoint apiEndpoint = retrofit.create(ApiEndPoint.class);

        Call<List<RiwayatModel>> call = apiEndpoint.getHistoryList();
        call.enqueue(new Callback<List<RiwayatModel>>() {
            @Override
            public void onResponse(Call<List<RiwayatModel>> call, Response<List<RiwayatModel>> response) {
                mutableLiveData.setValue((ArrayList<RiwayatModel>) response.body());
            }

            @Override
            public void onFailure(Call<List<RiwayatModel>> call, Throwable t) {
            }
        });
    }

    public LiveData<ArrayList<RiwayatModel>> getTodayListData() {
        return mutableLiveData;
    }
}
