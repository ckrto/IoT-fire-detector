package com.example.maptest.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.example.maptest.thread.BeaconThread;
import com.example.maptest.websocket.TrustOkHttpClientUtil;
import com.example.maptest.websocket.WebSocketListener;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class MyService extends Service implements BeaconConsumer {
    private BeaconManager beaconManager;
    private List<Beacon> beaconList = new ArrayList<>();
    private BeaconThread beaconThread;
    private Thread thread;
    private OkHttpClient client;
    private WebSocket webSocket;
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //beacon 설정
        beaconManager = BeaconManager.getInstanceForApplication(this);
        beaconManager.getBeaconParsers().add(new BeaconParser().
                setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));
        beaconManager.bind(this);
        Log.d("service", "oncreate 실행");
        //socket 설정
        client = TrustOkHttpClientUtil.getUnsafeOkHttpClient().build();
        Request request = new Request.Builder().url("wss://59.18.155.12:8080/beacon").build();
        okhttp3.WebSocketListener listener = new WebSocketListener();
        //socket 연결
        webSocket = client.newWebSocket(request, listener);
        client.dispatcher().executorService().shutdown();

        beaconThread = new BeaconThread(beaconList, webSocket);
        thread = new Thread(beaconThread);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("service", "onStartCommand 실행");
        onBeaconServiceConnect();
        System.out.println("myservice"+Thread.currentThread());
        thread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("service", "onDestroy 실행");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("service", "onBind 실행");
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.setRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                if (beacons.size() > 0) {
                    beaconList.clear();

                    //비콘 리스트에 비콘 정보 저장
                    for (Beacon beacon : beacons) {
                        beaconList.add(beacon);
                        System.out.println("beacon id: "+beacon.getId1());
                    }
                }
            }
        });
        try {
            beaconManager.startRangingBeaconsInRegion(new Region("beacon", null, null, null));
        } catch (RemoteException e) {}
    }
}