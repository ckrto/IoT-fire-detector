package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("/ibeacon/")
public class ServerTest extends HttpServlet {
   private static final long serialVersionUID = 1L;

   private List<BeaconData> datas;

/**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
	   
	   
	   
	   request.setCharacterEncoding("utf-8");
	   response.setContentType("text/html;charset=utf-8");
	   PrintWriter out = response.getWriter();
	   
	   //String start = request.getParameter("start");
	   String uuid = request.getParameter("uuid");
	   String dis = request.getParameter("dis");
	   String rssi = request.getParameter("rssi");
	   String minor = request.getParameter("minor");

	   String struuid = "UUID : " + uuid;
	   String strbr = "<br>";
	   String strdis = "DISTANCE : " + dis;
	   String strrssi = "RSSI : " + rssi;
	   String strminor = "MINOR : " + minor;

	   System.out.println(struuid);
	   System.out.println(strdis);
	   System.out.println(strrssi);
	   System.out.println(strminor);

	   String data = "<html>";
	   data += struuid;
	   data += strbr;
	   data += strdis;
	   data += strbr;
	   data += strrssi;
	   data += strbr;
	   data += strminor;
	   data += "</html>";
	   out.print(data);
	   

	   //파일 생성

	   String enter = "\n";
	   String filename = "result";
	   String beacon_result1 = "5505_";
	   String beacon_result2 = "5510_";
	   String beacon_result3 = "5520_";
	   String extend = ".txt";
   
	   

	   try {
		   	   
		   byte[] by_uuid = struuid.getBytes();
		   byte[] byenter = enter.getBytes();
		   byte[] by_dis = strdis.getBytes();
		   byte[] by_rssi = strrssi.getBytes();
		   byte[] by_minor = strminor.getBytes();
		   
		   //5505
		   if(minor.equals("55505")) {
			   File dir = new File("C:/Users/J5409/Desktop/2021-1 프로젝트/ibeacon6/5505");
			   File files[] = dir.listFiles();
			   int fileNo = files.length + 1;
			   
			   OutputStream output1 = new FileOutputStream("C:/Users/J5409/Desktop/2021-1 프로젝트/ibeacon6/5505/" + beacon_result1 + fileNo + extend);
			   
			   output1.write(by_uuid);
			   output1.write(byenter);
			   output1.write(by_dis);
			   output1.write(byenter);
			   output1.write(by_rssi);
			   output1.write(byenter);
			   output1.write(by_minor);
			   output1.write(byenter);
			   output1.close();
			   
		   } else if(minor.equals("55510")) {
			   File dir = new File("C:/Users/J5409/Desktop/2021-1 프로젝트/ibeacon6/5510");
			   File files[] = dir.listFiles();
			   int fileNo = files.length + 1;
			   
			   OutputStream output2 = new FileOutputStream("C:/Users/J5409/Desktop/2021-1 프로젝트/ibeacon6/5510/" + beacon_result2 + fileNo + extend);
			   
			   output2.write(by_uuid);
			   output2.write(byenter);
			   output2.write(by_dis);
			   output2.write(byenter);
			   output2.write(by_rssi);
			   output2.write(byenter);
			   output2.write(by_minor);
			   output2.write(byenter);
			   output2.close();
			   
		   } else if(minor.equals("55520")){
			   File dir = new File("C:/Users/J5409/Desktop/2021-1 프로젝트/ibeacon6/5520");
			   File files[] = dir.listFiles();
			   int fileNo = files.length + 1;
			   
			   OutputStream output3 = new FileOutputStream("C:/Users/J5409/Desktop/2021-1 프로젝트/ibeacon6/5520/" + beacon_result3 + fileNo + extend);
			   
			   output3.write(by_uuid);
			   output3.write(byenter);
			   output3.write(by_dis);
			   output3.write(byenter);
			   output3.write(by_rssi);
			   output3.write(byenter);
			   output3.write(by_minor);
			   output3.write(byenter);
			   output3.close();

		   } else {
			   File dir = new File("C:/Users/J5409/Desktop/2021-1 프로젝트/ibeacon6");
			   File files[] = dir.listFiles();
			   int fileNo = files.length + 1;
			   
			   OutputStream output = new FileOutputStream("C:/Users/J5409/Desktop/2021-1 프로젝트/ibeacon6/" + filename + fileNo + extend);
			   output.write(by_uuid);
			   output.write(byenter);
			   output.write(by_dis);
			   output.write(byenter);
			   output.write(by_rssi);
			   output.write(byenter);
			   output.write(by_minor);
			   output.write(byenter);
			   output.close();
		   }

	   } catch (FileNotFoundException e) {
		   e.printStackTrace();
	   } catch (IOException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	   }
	   
	   
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      
   }
  
   private void checkCollectData(HttpServletRequest request) {
	   BeaconData data = new BeaconData();
	   data.setUuid(Double.parseDouble(request.getParameter("uuid")));
	   data.setDis(Double.parseDouble(request.getParameter("dis")));
	   data.setRssi(Double.parseDouble(request.getParameter("rssi")));
	   data.setRssi(Double.parseDouble(request.getParameter("minor")));
	   this.datas.add(data);
   }
   /*
   private void MapTestData(HttpServletRequest request, HttpServletResponse response) {
	   String frame = request.getParameter("frame");
	   String wall = request.getParameter("wall");
	   String fire = request.getParameter("fire");
	   String user = request.getParameter("user");
	   String exit = request.getParameter("exit");
	   String door = request.getParameter("door");
	   String beacon = request.getParameter("beacon");
	   
	   MapDataDTO mapdata = new MapDataDTO();
	   mapdata.setFrame(frame);
	   mapdata.setWall(wall);
	   mapdata.setFire(fire);
	   mapdata.setUser(user);
	   mapdata.setExit(exit);
	   mapdata.setDoor(door);
	   mapdata.setBeacon(beacon);
	   
	   MapData mapData = new MapData();
	   mapData.Map_Arr(mapdata);
	   
	   //response.sendRedirect("http://localhost:8080/MapArray.jsp");
	   
   }
   
   */
}