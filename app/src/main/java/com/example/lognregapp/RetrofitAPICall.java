package com.example.lognregapp;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitAPICall {

    @POST("Verification")
    Call<Void> Verification(@Body User user);

    @POST("Autorisation")
    Call<Void> Autorisation(@Body User user);

}
