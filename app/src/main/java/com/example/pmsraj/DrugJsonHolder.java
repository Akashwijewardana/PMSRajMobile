package com.example.pmsraj;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DrugJsonHolder {

    @GET("getallbynames")
    Call<List<Drug>> getDrugs();
}
