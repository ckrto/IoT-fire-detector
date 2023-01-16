package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MapTestData
 */
@WebServlet("/MapTestData")
public class MapTestData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MapTestData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
//		   String beacon = request.getParameter("beacon");
//		   String strArr = request.getParameter("strArr");
//		   
//		   ArrayList<Integer> list1 = new ArrayList<Integer>();
//		   ArrayList<ArrayList<Integer>> list2 = new ArrayList<ArrayList<Integer>>();
//		   
//		   MapDataDTO mapdata = new MapDataDTO();
//		   mapdata.setBeacon(beacon);
//		   //mapdata.setStrArr(strArr);	   
//		   
//		   //CSV 만들기
//		   String filePath = "C:/Users/J5409/Desktop/MapData.CSV";
//		   
//		   File file = null;
//		   BufferedWriter bw = null;
//		   String NEWLINE = System.lineSeparator();
//		   try {
//			   file = new File(filePath);
//			   bw = new BufferedWriter(new FileWriter(file));
//			   
//			   bw.write(strArr);
//			   bw.write(NEWLINE);
//			   
//			   bw.flush();
//			   bw.close();
//			   
//			   //csv파일 계속 만들기
//			   String extend = ".CSV";
//			   File csv = new File("C:/Users/J5409/Desktop/2021-1 프로젝트/CSV");
//			   File files[] = csv.listFiles();
//			   int fileNum = files.length + 1;
//			   
//			   byte[] by_strArr = strArr.getBytes();
//			   
//			   OutputStream output = new FileOutputStream("C:/Users/J5409/Desktop/2021-1 프로젝트/CSV/" + fileNum + extend);
//			   output.write(by_strArr);
//			   output.close();
//			   
//			   
//		   } catch (Exception e) {
//			   e.printStackTrace();
//		   } 
//		   
//		   //CSV에서 읽어오기
//		   BufferedReader br = null;
//		   String line;
//		   String extend = ".CSV";
//		   File csv = new File("C:/Users/J5409/Desktop/2021-1 프로젝트/CSV");
//		   File files[] = csv.listFiles();
//		   int fileName = files.length;
//		   String path = "C:/Users/J5409/Desktop/2021-1 프로젝트/CSV/" + fileName + extend;
////			
//		   
//		   try {
//				//br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
//				br = new BufferedReader(new FileReader(path));
//
//				while((line = br.readLine()) != null) {
//					String[] temp = line.split(",");
//					
//					for(int i = 0; i<temp.length; i++) {
//						//System.out.print(temp[i]);
//						list1.add(Integer.parseInt(temp[i]));
//						
//						if(i==temp.length-1) {
//							list2.add(list1);
//							list1 = new ArrayList<Integer>();
//							//System.out.print(",");
//						} 
//						else {
//							//System.out.println();
//						}
//
//					}
//					
//					
//				}
//				//System.out.println();
//				for(int i=0; i < list2.size(); i++) {
//					for(int j=0; j < list2.get(i).size(); j++) {
//						System.out.print(list2.get(i).get(j));
//						
//					}
//					System.out.println(" ");
//				}
//				System.out.println();
//				
//				
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

		}
	
	
		   
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
