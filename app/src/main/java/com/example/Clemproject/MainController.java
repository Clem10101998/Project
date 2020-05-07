package com.example.Clemproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;

    public MainController(MainActivity mainActivity, Gson gson, SharedPreferences sharedPreferences){
        this.view = mainActivity;
        this.gson = gson;
    this.sharedPreferences = sharedPreferences;
    }
    public void OnStart(){

        List<Donnees> DonneesList = getDataFromCache();

        if ( DonneesList!= null){
            view.showList(DonneesList);
        }else{
            makeApiCall();
        }
    }

    private void makeApiCall(){
        //Gson gson = new GsonBuilder()
                //.setLenient()
               // .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Api donneesApi = retrofit.create(Api.class);
        //Log.d("Clem", "BEFORE CALLBACK");
        Call<RestApiResponse> call = donneesApi.getDonneesResponse();
        call.enqueue(new Callback<RestApiResponse>() {
            @Override
            public void onResponse(Call<RestApiResponse> call, Response<RestApiResponse> response) {

                //Log.d("Clem", "INSIDE CALLBACK");
                if(response.isSuccessful() && (response.body() != null)) {

                    List<Donnees> DonneesList;
                    DonneesList = response.body().getCountries();
                    //Toast.makeText(getApplicationContext(), "API Success", Toast.LENGTH_SHORT).show();
                    saveList(DonneesList);
                    view.showList(DonneesList);
                }else{
                    view.showError();
                }
            }


            @Override
            public void onFailure(Call<RestApiResponse> call, Throwable t) {

                view.showError();
            }
        });
//Log.d("Clem", "AFTER CALLBACK");
    }

    private void saveList(List<Donnees> DonneesList) {
        String jsonString = gson.toJson(DonneesList);
        sharedPreferences
                .edit()
                .putString(Constants.KEY_DONNEES_LIST, jsonString)
                .apply();
        //Toast.makeText(getApplicationContext(), "List saved", Toast.LENGTH_SHORT).show();
    }

    private List<Donnees> getDataFromCache() {
        String jsonDonnees = sharedPreferences.getString(Constants.KEY_DONNEES_LIST, null);
        if(jsonDonnees == null){
            return null;
        }else{
            Type listType = new TypeToken<List<Donnees>>(){}.getType();
            return gson.fromJson(jsonDonnees, listType);
        }

    }

    public void onItemClick(Donnees donnees){

    }

    public void onButtonAClick(){

    }

    public void onButtonBClick(){

    }

}
