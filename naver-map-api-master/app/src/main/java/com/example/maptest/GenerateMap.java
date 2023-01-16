package com.example.maptest;

import android.graphics.Color;

import com.example.maptest.data.IndoorMap;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.overlay.PolygonOverlay;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GenerateMap {

    public PolygonOverlay generate(IndoorMap list) throws JSONException {
        PolygonOverlay polygonOverlay = new PolygonOverlay();
        JSONArray jsonarr = new JSONArray(list.getPosition());
        List<LatLng> latLngList = new ArrayList<>();

        for(int i=0; i<jsonarr.length(); i++){
            String str = jsonarr.getString(i);
            String str2 = str.substring(1, str.length()-2);
            String[] strarr = str2.split(",");
            latLngList.add(new LatLng(Double.parseDouble(strarr[1]), Double.parseDouble(strarr[0])));
        }
        System.out.println("lstlnglist : " + latLngList.size());
        polygonOverlay.setCoords(latLngList);
        polygonOverlay.setOutlineWidth(1);
        polygonOverlay.setColor(Color.rgb(245,248,251));
        polygonOverlay.setMinZoom(17);
        polygonOverlay.setMinZoomInclusive(true);
        polygonOverlay.setMaxZoom(21);
        polygonOverlay.setMaxZoomInclusive(false);

        return polygonOverlay;
    }
}
