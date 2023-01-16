package test;

import java.util.ArrayList;

import org.json.JSONArray;

import main.EmergencyTest;
import main.BaseMap;



public class FireSensorWiehghtChange {
   private BaseMap basemap;
  //private WeightMap weightmap;
   private SensorMap sensormap;
   
   public FireSensorWiehghtChange() {
      this.basemap = new BaseMap();
      //this.weightmap = new WeightMap();
      this.sensormap = new SensorMap();
   }
   
   public int[][] WeightChange(ArrayList firearr) {
      int[][] resultmap = basemap.getMap_arr();
      for(int i=0; i<firearr.size(); i++) {
    	  resultmap[sensormap.getSensormap().get(firearr.get(i)).getX()][sensormap.getSensormap().get(firearr.get(i)).getY()] = 999;

      }
      
      
      // resultmap 출력
      for(int i=0; i<basemap.getMap_arr().length; i++) {
         for(int j=0; j<basemap.getMap_arr()[i].length; j++) {
            System.out.print(resultmap[i][j] + " ");
         }
         System.out.println();
         
      }
      
      System.out.println("-----------------");
      return resultmap;
   }
   
   public int[][] WeightChange2(ArrayList firearr) {
	      int[][] resultmap = basemap.getMap_arr();
	      for(int i=0; i<firearr.size(); i++) {
	         resultmap[sensormap.getSensormap().get(firearr.get(i)).getX()][sensormap.getSensormap().get(firearr.get(i)).getY()] = 0;
	         
	      }
	      
	      // resultmap 출력
	      for(int i=0; i<basemap.getMap_arr().length; i++) {
	         for(int j=0; j<basemap.getMap_arr()[i].length; j++) {
	            System.out.print(resultmap[i][j] + " ");
	         }
	         System.out.println();
	         
	      }
	      System.out.println("-----------------");
	      return resultmap;
	   }
   
   
   public static void main(String[] args) {
      FireSensorWiehghtChange fswc = new FireSensorWiehghtChange();
      EmergencyTest emer = new EmergencyTest();
      ArrayList<String> firearr = new ArrayList<>();
      firearr = emer.getFirearr();
      fswc.WeightChange(firearr);
   }
}