package com.example.maptest.restapi;

import com.example.maptest.data.FireSensor;
import com.example.maptest.data.IndoorMap;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitAPI {
    @GET("get/sensor")
    Call<List<FireSensor>> getData(@Query("beaconid") String id);

    @GET("get/indoor")
    Call<List<IndoorMap>> getMap(@Query("x") double x, @Query("y") double y);
}
