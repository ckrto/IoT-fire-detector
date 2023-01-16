package test;

import java.util.HashMap;

public class SensorMap {
   HashMap<String, SensorData> sensormap;
   
   public SensorMap() {
      this.sensormap = new HashMap<String, SensorData>();
//      sensormap.put("flame1", new SensorData(10, 22)); //test
      sensormap.put("flame1", new SensorData(10, 22));  // 완강기 쪽(^3^)
//      sensormap.put("flame1", new SensorData(23, 22)); // 사물함 쪽(^3^)   
      sensormap.put("flame1", new SensorData(19, 1)); // 5414 쪽(^3^) 

      //여러개 받을 수 있게.
   }
   
   

   public HashMap<String, SensorData> getSensormap() {
	   
      return sensormap;
   }
   

}
