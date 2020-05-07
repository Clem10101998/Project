package com.example.Clemproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    //@GET("fakespokemonapi.json")
    //@GET("/api/v2/pokemon")
    @GET("summary")
     Call<RestApiResponse> getDonneesResponse();

}
