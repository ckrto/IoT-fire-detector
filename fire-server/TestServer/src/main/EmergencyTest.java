package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import test.FireSensorWiehghtChange;
import test.SensorMap;

/**
 * Servlet implementation class EmergencyTest
 */
@WebServlet("/EmergencyTest/*")
public class EmergencyTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<String> firearr;
	private ArrayList<int[]> fireposition;
	private int fireStatus;
	// private int fireStatus1;
	private int gasStatus;
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmergencyTest() {
		super();
		this.fireStatus = 0;
		// this.fireStatus1 = 0;
		this.gasStatus = 0;
		this.firearr = new ArrayList<String>();
//		this.fireposition = new ArrayList<int[]>();
//		this.fireposition.add(new int[] {1,22});
		//this.fireposition.add(new int[] {23, 22});
		//this.fireposition.add(new int[] {19, 1});
		
		
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.checkURL(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.checkURL(request, response);
	}

	private void checkURL(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURI();

		if(url.equals("/TestServer/EmergencyTest/sendData")) {
			this.sendData(request, response);
		} else if(url.equals("/TestServer/EmergencyTest/sendData1")) {
			this.sendData1(request, response);
		} else if(url.equals("/TestServer/EmergencyTest/getCurrentStatus")) {
			this.getCurrentStatus(request, response);
		} else if(url.equals("/TestServer/EmergencyTest/json")) {
	         this.json(request, response);
		} else if(url.equals("/TestServer/EmergencyTest/MapTestData")) {
			this.MapTestData(request, response);
		}
	}

	private void getCurrentStatus(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.getWriter().println(this.fireStatus);
			// response.getWriter().println(this.fireStatus1);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * private void sendData(HttpServletRequest request, HttpServletResponse
	 * response) { // String mq5 = request.getParameter("MQ5"); // String mq6 =
	 * request.getParameter("MQ6"); String flame1 = request.getParameter("flame1");
	 * 
	 * if (Integer.parseInt(flame1) == 0 && Integer.parseInt(mq5) > 400 ) {
	 * this.fireStatus = 0; System.out.println("찬혁 = " + flame1); // 아두이노 Com_3 }
	 * else { this.fireStatus = 1; } }
	 * 
	 * private void sendData1(HttpServletRequest request, HttpServletResponse
	 * response) { // String mq5 = request.getParameter("MQ5"); // String mq6 =
	 * request.getParameter("MQ6"); // String flame2 =
	 * request.getParameter("flame2"); String flame3 =
	 * request.getParameter("flame3");
	 * 
	 * if (Integer.parseInt(flame3) == 0 && Integer.parseInt(mq5) > 400 ) {
	 * this.fireStatus = 1; System.out.println("종오 = " + flame3); } else {
	 * this.fireStatus = 0; } }
	 */
	private void sendData(HttpServletRequest request, HttpServletResponse response) {
	      // String mq5 = request.getParameter("MQ5");
	      // String mq6 = request.getParameter("MQ6");
	      String flame1 = request.getParameter("flame1");
	      
	      

	      if (Integer.parseInt(flame1) == 0 /* && Integer.parseInt(mq5) > 400 */) {
	         this.fireStatus = 0;
	         System.out.println("찬혁 = " + flame1); // 아두이노 Com_3
	         firearr.add("flame1");
	         
	         
	         System.out.println(fireposition);
	         FireSensorWiehghtChange fswc = new FireSensorWiehghtChange();
			 fswc.WeightChange(firearr);
			 fswc.WeightChange(firearr);
			  
			 this.json(request, response);

	      } else {
	         this.fireStatus = 1;
	         firearr.remove("flame1");
	         
	         FireSensorWiehghtChange fswc = new FireSensorWiehghtChange();
			 fswc.WeightChange2(firearr);
			 
			 
			 this.json(request, response);
	         
	      } 
	   }

	   private void sendData1(HttpServletRequest request, HttpServletResponse response) {
	      // String mq5 = request.getParameter("MQ5");
	      // String mq6 = request.getParameter("MQ6");
	      // String flame2 = request.getParameter("flame2");
	      String flame3 = request.getParameter("flame3");
	      
	      if (Integer.parseInt(flame3) == 0 /* && Integer.parseInt(mq5) > 400 */) {
	         this.fireStatus = 0;
	         System.out.println("종오 = " + flame3);
	         firearr.remove("flame3");
	         
	      } else {
	         this.fireStatus = 1;
	         firearr.add("flame3");
	      }
	   }

	   public ArrayList<String> getFirearr() {
	      return firearr;
	   }
	   
	   private void json(HttpServletRequest request, HttpServletResponse response) {
		      //int i=2;
		      BaseMap basemap = new BaseMap();
		      SensorMap sensormap = new SensorMap();
		      fireposition = new ArrayList<int[]>();
		      
		      int[][] resultmap = basemap.getMap_arr();
		      //int[][] resultmap = mapdata.getStrArr();
		      //System.out.println("ss");
		      
		      //int[] xy = new int[2];
		      int[] xy = null;
		      
		      for(int i=0; i<resultmap.length; i++) {
		    	  for (int j=0; j<resultmap[0].length; j++) {
		    		  if(resultmap[i][j] == 999) {
		    			  xy = new int[2];
		    			  xy[0] = i;
		    			  xy[1] = j;
		    		  }
		    	  }
		      }
		      
		      fireposition.add(xy);
		      
		      JSONObject jsonobject = new JSONObject();
		      JSONArray jsonmap = new JSONArray();
		      
			  JSONArray jsonPosition = new JSONArray();
			  jsonPosition.put(fireposition);

		      jsonmap.put(resultmap);
		      
		      jsonobject.put("map", jsonmap);
		      jsonobject.put("fireposition", jsonPosition);
		      //System.out.println("json");
				
		      try {
		    	  
		    	  response.setIntHeader("Refresh", 1);
		    	  response.getWriter().println(jsonobject.toString());
		    	  } catch (IOException e) {
		    		  // TODO Auto-generated catch block
		    		  e.printStackTrace();
		    		  }
		      }
	   
	   protected void MapTestData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   String beacon = request.getParameter("beacon");
		   String strArr = request.getParameter("strArr");
		   
		   ArrayList<Integer> list1 = new ArrayList<Integer>();
		   ArrayList<ArrayList<Integer>> list2 = new ArrayList<ArrayList<Integer>>();
		   
		   MapDataDTO mapdata = new MapDataDTO();
		   mapdata.setBeacon(beacon);
		   //mapdata.setStrArr(strArr);	   
		   
		   //CSV 만들기
		   String filePath = "C:/Users/J5409/Desktop/MapData.CSV";
		   
		   File file = null;
		   BufferedWriter bw = null;
		   String NEWLINE = System.lineSeparator();
		   try {
			   file = new File(filePath);
			   bw = new BufferedWriter(new FileWriter(file));
			   
			   bw.write(strArr);
			   bw.write(NEWLINE);
			   
			   bw.flush();
			   bw.close();
			   
			   //csv파일 계속 만들기
			   String extend = ".CSV";
			   File csv = new File("C:/Users/J5409/Desktop/2021-1 프로젝트/CSV");
			   File files[] = csv.listFiles();
			   int fileNum = files.length + 1;
			   
			   byte[] by_strArr = strArr.getBytes();
			   
			   OutputStream output = new FileOutputStream("C:/Users/J5409/Desktop/2021-1 프로젝트/CSV/" + fileNum + extend);
			   output.write(by_strArr);
			   output.close();
			   
			   
		   } catch (Exception e) {
			   e.printStackTrace();
		   } 
		   
		   //CSV에서 읽어오기
		   BufferedReader br = null;
		   String line;
		   String extend = ".CSV";
		   File csv = new File("C:/Users/J5409/Desktop/2021-1 프로젝트/CSV");
		   File files[] = csv.listFiles();
		   int fileName = files.length;
		   String path = "C:/Users/J5409/Desktop/2021-1 프로젝트/CSV/" + fileName + extend;
		   
		   try {
				//br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
				br = new BufferedReader(new FileReader(path));
				

				while((line = br.readLine()) != null) {
					String[] temp = line.split(",");
					
					for(int i = 0; i<temp.length; i++) {
						//System.out.print(temp[i]);
						list1.add(Integer.parseInt(temp[i]));
						
						if(i==temp.length-1) {
							list2.add(list1);
							list1 = new ArrayList<Integer>();
							//System.out.print(",");
						} 
						else {
							//System.out.println();
						}

					}
					
					
				}
				
				//배열 출력
				for(int i=0; i < list2.size(); i++) {
					for(int j=0; j < list2.get(i).size(); j++) {
						System.out.print(list2.get(i).get(j));
					}
					System.out.println(" ");
				}
				System.out.println();
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	   

}
