package com.example.picovid_19.api;


import com.example.picovid_19.Model.IndonesiaModel;
import com.example.picovid_19.Model.GlobalModel;
import com.example.picovid_19.Model.RiwayatModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndPoint {
    //Mangambil alamat dari class Api
    @GET(Api.END_POINT_GLOBAL_HISTORY)
    Call<List<RiwayatModel>> getHistoryList();

    @GET(Api.END_POINT_SUMMARY_GLOBAL)
    Call<GlobalModel> getSummaryWorld();

    @GET(Api.END_POINT_IDN)
    Call<IndonesiaModel> getSummaryIdn();

}
