package com.example.Clemproject;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {

    private static Gson gsonInstance;
    private static Api donneesApiInstance;
    private static SharedPreferences sharedPreferencesInstance;

    public static Gson getGson(){
        if(gsonInstance == null){
            gsonInstance = new GsonBuilder()
                    .setLenient()
                    .create();
        }
        return gsonInstance;
    }
public static Api getDonneesApi(){
if(donneesApiInstance == null){
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(getGson()))
            .build();

    donneesApiInstance = retrofit.create(Api.class);
}
return donneesApiInstance;
}

    public static SharedPreferences getSharedPreferences(Context context){
        if(sharedPreferencesInstance == null){
            sharedPreferencesInstance =  context.getSharedPreferences("application_esiea", Context.MODE_PRIVATE);

        }
        return sharedPreferencesInstance;
    }

}
