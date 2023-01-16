package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

public class ServerTest {
	 public static void main(String[] args) throws Exception {
	        // URL 생성
	        URL url = new URL("http://httpbin.org/post");
	         
	        // URLConnection 객체 생성
	        java.net.URLConnection uc = url.openConnection();
	         
	        uc.setDoOutput(true); //doOutput 필드값을 treu로 설정해서 출력모드로 바꾸었다.
	         
	        //출력 스트림 생성
	        OutputStreamWriter ow = new OutputStreamWriter(uc.getOutputStream()); 
	         
	        ow.write("Name = Hong Gil Dong & Number=34"); // 데이터를 적어주었다.
	        ow.close();
	         
	        BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
	         
	        String str = "";
	         
	        while( (str = in.readLine()) != null ) {
	            System.out.println(str);
	             
	        }
	            in.close();
	    }

}
