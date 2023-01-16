package main;

import org.json.JSONArray;
import org.json.JSONObject;

public class MapJson {
	private BaseMap basemap;
	private MapDataDTO mapdata;
	private int[][] map;
	//private String[] map;
	public MapJson() {
		this.basemap = new BaseMap();
		this.mapdata = new MapDataDTO();
		map = basemap.getMap_arr();
		//map = mapdata.getStrArr();
		//map = mapdata.getStrArr();
	}
	
	public void Jsonparse() {
		JSONObject jsonob = new JSONObject();
		JSONArray jsonarr = new JSONArray();
		
		jsonarr.put(this.map);
		jsonob.put("map", jsonarr);
		
		System.out.println(jsonob.toString());
	}
	
	 public static void main(String[] args) {
		MapJson mapjson = new MapJson();
		mapjson.Jsonparse();
	}
}