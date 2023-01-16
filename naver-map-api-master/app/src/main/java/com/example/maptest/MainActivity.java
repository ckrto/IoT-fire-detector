package com.example.maptest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.maptest.data.IndoorMap;
import com.example.maptest.restapi.IgnoreSSL;
import com.example.maptest.restapi.RestApi;
import com.example.maptest.restapi.RetrofitAPI;
import com.example.maptest.service.MyService;
import com.example.maptest.thread.GenerateThread;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.util.FusedLocationSource;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private MapView mapView;
    private FusedLocationSource mLocationSource;
    private static final int PERMISSION_REQUEST_CODE = 100;
    private static final String[] PERMISSIONS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    private NaverMap mNaverMap;
    private RestApi restApi;
    private RetrofitAPI retrofitAPI;
    private GenerateMap generateMap;
    private HashSet<String> mapHashSet;

    private Handler mapHandler;
    private List<String> buildList;
    private List<IndoorMap> indoorMapList;
    private Runnable mapRunnable;
    private Thread mapThread;

    private ExecutorService executorService;
    private GenerateThread generateThread;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        restApi = new RestApi();
        retrofitAPI = restApi.getRetrofitAPI();

        generateMap = new GenerateMap();
        mapHashSet = new HashSet<>();
        generateThread = new GenerateThread();
        buildList = new ArrayList<>();
        indoorMapList = new ArrayList<>();
        executorService = Executors.newFixedThreadPool(4);
        //rest api
        //ssl 인증 무시
        /*
        ignoreSSL = new IgnoreSSL();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://59.18.155.12:8443/mapserver/")
                .client(ignoreSSL.getUnsafeOkHttpClient().build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        retrofitAPI.getData().enqueue(new Callback<List<FireSensor>>() {
            @Override
            public void onResponse(Call<List<FireSensor>> call, Response<List<FireSensor>> response) {
                if(response.isSuccessful()) {
                    // 서버로부터 전달받은 데이터
                    List<FireSensor> sensorList = response.body();

                    System.out.println("성공");
                }
            }

            @Override
            public void onFailure(Call<List<FireSensor>> call, Throwable t) {
                t.printStackTrace();
                System.out.println("실패");
            }
        });
        */
        //백그라운드 beacon service
        Intent intent = new Intent(getApplicationContext(), MyService.class);
        startService(intent);
        System.out.println("MainThread : " + Thread.currentThread());

        FragmentManager fm = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment)fm.findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);

        /*mapRunnable = new GenerateThread(){
            @Override
            public void run(){
                for(IndoorMap indoorMap : indoorMapList){
                    if(!mapHashSet.contains(indoorMap.getBuilding())){
                        System.out.println("맵 추가");
                        try {
                            //polygonOverlayList.add(generateMap.generate(indoorMap));
                            generateMap.generate(indoorMap).setMap(mNaverMap);
                            buildList.add(indoorMap.getBuilding());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                for(String building : buildList){
                    mapHashSet.add(building);
                }
            }
        };*/
        generateThread = new GenerateThread();

    }

    //맵 설정(유형, 오버레이, 버튼)
    @UiThread
    @Override
    public void onMapReady(@NonNull final NaverMap naverMap) {

        mNaverMap = naverMap;
        // 현재 위치 버튼 안보이게 설정
        UiSettings uiSettings = naverMap.getUiSettings();
        uiSettings.setLocationButtonEnabled(true);

        // NaverMap 객체 받아서 NaverMap 객체에 위치 소스 지정
        mNaverMap.setLocationSource(mLocationSource);
        
        // 권한확인. 결과는 onRequestPermissionsResult 콜백 매서드 호출
        ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_REQUEST_CODE);
        
        // 화면 움직인후 멈출때 & 카메라 줌 레벨 17이상 일때
        // Rest api 현위치 기중 맵 요청 후 오버레이
        naverMap.addOnCameraIdleListener(() -> {
            if(naverMap.getCameraPosition().zoom >= 17){
                generateThread.setPosition(mNaverMap.getCameraPosition().target.latitude, mNaverMap.getCameraPosition().target.longitude);
                Future<List<IndoorMap>> future = executorService.submit(generateThread);

                try {
                    indoorMapList = future.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(indoorMapList != null){
                    for(IndoorMap indoorMap : indoorMapList){
                        if(!mapHashSet.contains(indoorMap.getBuilding())){
                            System.out.println("맵 추가");
                            try {
                                //polygonOverlayList.add(generateMap.generate(indoorMap));
                                generateMap.generate(indoorMap).setMap(mNaverMap);
                                buildList.add(indoorMap.getBuilding());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    for(String building : buildList){
                        mapHashSet.add(building);
                    }
                }
            }
        });
    }

    /*//GPS 현재위치
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // request code와 권한획득 여부 확인
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mNaverMap.setLocationTrackingMode(LocationTrackingMode.Follow);
            }
        }
    }*/
    // json 파일 읽어오기
    /*public List<LatLng>[] getJson(String filename){
        AssetManager assetManager = getAssets();
        List<LatLng>[] list = new List[0];
        try{
            InputStream is = assetManager.open(filename);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);

            StringBuffer buffer = new StringBuffer();
            String line = reader.readLine();
            while(line != null){
                buffer.append(line + "\n");
                line=reader.readLine();
            }
            String jsonData = buffer.toString();


            JSONArray jsonArray = new JSONArray(jsonData);
            list = new ArrayList[jsonArray.length()];

            for(int i=0; i<jsonArray.length(); i++){
                list[i] = new ArrayList<>();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                JSONObject geometry = (JSONObject) jsonObject.get("geometry");
                JSONArray jsonArray1 = geometry.getJSONArray("coordinates");

                for(int j=0; j<jsonArray1.getJSONArray(0).length(); j++){
                    String str = jsonArray1.getJSONArray(0).get(j).toString();
                    String str2 = str.substring(1, str.length()-2);
                    String[] strarr = str2.split(",");
                    list[i].add(new LatLng(Double.parseDouble(strarr[1]),Double.parseDouble(strarr[0])));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }*/
}