package com.example.maptest.thread;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;

import com.example.maptest.data.FireSensor;
import com.example.maptest.restapi.RestApi;
import com.example.maptest.restapi.RetrofitAPI;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;

import java.util.ArrayList;
import java.util.List;

import okhttp3.WebSocket;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeaconThread implements BeaconConsumer,Runnable {
    private WebSocket webSocket;
    private List<Beacon> beaconList;

    public BeaconThread(List<Beacon> beaconList, WebSocket webSocket){
        this.beaconList = beaconList;
        this.webSocket = webSocket;
    }

    @Override
    public void run(){
        while(true){
            if(beaconList.size() > 0){
                for(Beacon beacon : beaconList){
                    webSocket.send(beacon.getId1().toString());
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBeaconServiceConnect() {

    }

    @Override
    public Context getApplicationContext() {
        return null;
    }

    @Override
    public void unbindService(ServiceConnection connection) {

    }

    @Override
    public boolean bindService(Intent intent, ServiceConnection connection, int mode) {
        return false;
    }
}
