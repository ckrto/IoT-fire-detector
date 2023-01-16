package json;


import org.json.JSONArray;
import org.json.JSONObject;

public class MapJson {
	private BaseMap basemap;
	private int[][] map;
	public MapJson() {
		this.basemap = new BaseMap();
		map = basemap.getMap_arr();
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
