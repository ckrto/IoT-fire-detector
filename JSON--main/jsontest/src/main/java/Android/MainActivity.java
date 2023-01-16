package com.example.json_test;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    private TextView tv_outPut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 위젯에 대한 참조.
        tv_outPut = (TextView) findViewById(R.id.tv_outPut);

        // URL 설정.
        String url = "http://59.18.155.12:8080/controllers/json";

        // AsyncTask를 통해 HttpURLConnection 수행.
        NetworkTask networkTask = new NetworkTask(url, null);
        networkTask.execute();
    }

    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values) {

            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... params) {

            String result; // 요청 결과를 저장할 변수.
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.
            try {
                // 웹서버 JSON 가져오기
                JSONObject jobject = new JSONObject(result);
                // JSON객체 map 데이터
                JSONArray jsonarr = jobject.optJSONArray("map");
                // map 데이터 인덱스
                JSONArray jsonarr2 = jsonarr.optJSONArray(0);
                // JSON map  ->  int[][]
                StringTokenizer stk = new StringTokenizer((String) jsonarr2.getString(0), "[,]");
                int[][] map = new int[jsonarr2.length()][stk.countTokens()];
                for(int i=0; i<jsonarr2.length(); i++) {
                    stk = new StringTokenizer((String) jsonarr2.getString(i), "[,]");
                    String[] strings = new String[stk.countTokens()];
                    int j = 0;
                    while (stk.hasMoreTokens()) {
                        strings[j] = stk.nextToken();
                        map[i][j] = Integer.parseInt(strings[j]);
                        j++;
                    }
                }
                //맵 출력
                result = "";
                for(int i=0; i<map.length; i++){
                    result += Arrays.toString(map[i]) + "\n";
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }



            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.
            tv_outPut.setText(s);
        }
    }
}
