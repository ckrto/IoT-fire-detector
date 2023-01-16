package com.example.maptest.restapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApi {
    private final String url = "https://59.18.155.12:8443/mapserver/";
    private IgnoreSSL ignoreSSL;
    private RetrofitAPI retrofitAPI;
    private Retrofit retrofit;

    public RestApi(){
        ignoreSSL = new IgnoreSSL();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://59.18.155.12:8443/mapserver/")
                .client(ignoreSSL.getUnsafeOkHttpClient().build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitAPI = retrofit.create(RetrofitAPI.class);
    }

    public RetrofitAPI getRetrofitAPI() {
        return retrofitAPI;
    }
}
