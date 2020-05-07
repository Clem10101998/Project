package com.example.Clemproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Toast;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    //private static final String BASE_URL = "https://pokeapi.co/";
    //private static final String BASE_URL = "https://raw.githubusercontent.com/Clem10101998/Android3A/master/";
    private static final String BASE_URL = "https://api.covid19api.com/";
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences;
    private Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
        //showList();
        sharedPreferences = getSharedPreferences("application_esiea", Context.MODE_PRIVATE);
        gson = new GsonBuilder()
                .setLenient()
                .create();
        List<Donnees> DonneesList = getDataFromCache();

        if ( DonneesList!= null){
            showList(DonneesList);
        }else{
            makeApiCall();
        }

         //makeApiCall();

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

    private void showList(List<Donnees> DonneesList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        /*List<String> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            input.add("Test" + i);
        }*/
        // define an adapter
        mAdapter = new ListAdapter(DonneesList);
        recyclerView.setAdapter(mAdapter);

    }

    private void makeApiCall(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
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
                    Toast.makeText(getApplicationContext(), "API Success", Toast.LENGTH_SHORT).show();
                    saveList(DonneesList);
                    showList(DonneesList);
                }else{
                    showError();
                }
            }


            @Override
            public void onFailure(Call<RestApiResponse> call, Throwable t) {

                showError();
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
        Toast.makeText(getApplicationContext(), "List saved", Toast.LENGTH_SHORT).show();
    }

    private void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }

}
