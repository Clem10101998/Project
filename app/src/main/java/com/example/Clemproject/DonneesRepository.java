package com.example.Clemproject;

import android.content.SharedPreferences;
import android.telecom.Call;

import retrofit2.Callback;

public class DonneesRepository {

    private Api api;
    private SharedPreferences sharedPreferences;

    public DonneesRepository(Api api, SharedPreferences sharedPreferences){
        this.api = api;
        this.sharedPreferences = sharedPreferences;
    }
public void getApiResponse(DonneesCallback callback){
         //Api.getApiResponse().enqueue(new Callback<RestApiResponse>);
}
}
