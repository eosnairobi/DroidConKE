package com.kotani.eosaccounts.Api;

import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    String BASE_URL = "https://mainnet.eosnairobi.io/";


    @GET("/v1/chain/get_info")
    Call<String> getInfo();


    @POST("/v1/chain/get_account")
    Call<ResponseBody> getAccount(@Body RequestBody params);


    @POST("/v1/chain/get_actions")
    Call<ResponseBody> getActions(@Body RequestBody params);
}
