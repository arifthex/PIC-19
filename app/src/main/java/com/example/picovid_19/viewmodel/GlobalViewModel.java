package com.example.picovid_19.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.picovid_19.Model.GlobalModel;
import com.example.picovid_19.api.ApiEndPoint;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GlobalViewModel extends ViewModel {

    private MutableLiveData<GlobalModel> mutableLiveData = new MutableLiveData<>();

    public void setSummaryGlobal() {
        //
        Retrofit retrofit = com.example.picovid_19.api.ApiService.getRetrofitService();
        ApiEndPoint apiEndpoint = retrofit.create(ApiEndPoint.class);
        Call<GlobalModel> call = apiEndpoint.getSummaryWorld();
        call.enqueue(new Callback<GlobalModel>() {
            @Override
            public void onResponse(Call<GlobalModel> call, Response<GlobalModel> response) {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GlobalModel> call, Throwable t) {
            }
        });

    }
    public LiveData<GlobalModel> getSummaryGlobal() {
        return mutableLiveData;
    }
}
