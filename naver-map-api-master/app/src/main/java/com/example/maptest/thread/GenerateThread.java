package com.example.maptest.thread;

import com.example.maptest.data.IndoorMap;
import com.example.maptest.restapi.RestApi;
import com.example.maptest.restapi.RetrofitAPI;

import java.util.List;
import java.util.concurrent.Callable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenerateThread implements Callable<List<IndoorMap>> {
    private double x;
    private double y;
    private RetrofitAPI retrofitAPI;
    private RestApi restApi;
    private List<IndoorMap> indoorMapList;

    public GenerateThread(){
        restApi = new RestApi();
        retrofitAPI = restApi.getRetrofitAPI();
    }

    public void setPosition(double x, double y){
        this.x = x;
        this.y = y;
    }

    @Override
    public List<IndoorMap> call() throws Exception {
        System.out.println("generateThread : " + Thread.currentThread());
        retrofitAPI.getMap(x, y)
                .enqueue(new Callback<List<IndoorMap>>() {
                    @Override
                    public void onResponse(Call<List<IndoorMap>> call, Response<List<IndoorMap>> response) {
                        indoorMapList = response.body();
                    }

                    @Override
                    public void onFailure(Call<List<IndoorMap>> call, Throwable t) {
                        System.out.println("실패");
                    }
                });

        return indoorMapList == null ? null : indoorMapList;
    }
}
