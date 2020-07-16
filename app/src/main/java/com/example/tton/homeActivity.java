package com.example.tton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class homeActivity extends AppCompatActivity {

    private Button btn_pw, btn_ds, btn_tem, btn_co;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btn_pw = (Button) findViewById(R.id.btn_pw);
        Button btn_ds = (Button)findViewById(R.id.btn_ds);
        Button btn_tem = (Button)findViewById(R.id.btn_tem);
        Button btn_co2 = (Button)findViewById(R.id.btn_co2);

        btn_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new BackgroundTask_pw().execute();
            }
        });

        btn_ds.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                new BackgroundTask_ds().execute();
            }
        });

        btn_tem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                new BackgroundTask_tem_hum().execute();
            }
        });

        btn_co2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                new BackgroundTask_co2().execute();

                /*
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("fg_text", 4);
                startActivity(intent);
                 */
            }
        });
    }

    class BackgroundTask_pw extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            target = "https://akftmalffk.cafe24.com/power_info.php";
        }


        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target);  //해당 URL로 접속
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));  //하나씩 읽어올 수 있도록
                String temp;  //매 열마다 입력을 받도록 한다
                StringBuilder stringBuilder = new StringBuilder();  //각 결과값을 매 열마다 담겨 stringBuilder안에 넣음
                while((temp = bufferedReader.readLine()) != null)  //모든 열을 읽어옴
                {
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values){
            super.onProgressUpdate(values);
        }

        @Override
        public void onPostExecute(String result){
            Intent intent = new Intent(homeActivity.this, powerActivity.class);
            intent.putExtra("powerList", result);
            homeActivity.this.startActivity(intent);
        }
    }

    class BackgroundTask_ds extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            target = "https://akftmalffk.cafe24.com/ds_info.php";  //######################################
        }


        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target);  //해당 URL로 접속
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));  //하나씩 읽어올 수 있도록
                String temp;  //매 열마다 입력을 받도록 한다
                StringBuilder stringBuilder = new StringBuilder();  //각 결과값을 매 열마다 담겨 stringBuilder안에 넣음
                while((temp = bufferedReader.readLine()) != null)  //모든 열을 읽어옴
                {
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values){
            super.onProgressUpdate(values);
        }

        @Override
        public void onPostExecute(String result){
            Intent intent = new Intent(homeActivity.this, dustActivity.class);  //######################################
            intent.putExtra("dustList", result);  //######################################
            homeActivity.this.startActivity(intent);
        }
    }

    class BackgroundTask_tem_hum extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            target = "https://akftmalffk.cafe24.com/tem_hum_info.php";  //######################################
        }


        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target);  //해당 URL로 접속
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));  //하나씩 읽어올 수 있도록
                String temp;  //매 열마다 입력을 받도록 한다
                StringBuilder stringBuilder = new StringBuilder();  //각 결과값을 매 열마다 담겨 stringBuilder안에 넣음
                while((temp = bufferedReader.readLine()) != null)  //모든 열을 읽어옴
                {
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values){
            super.onProgressUpdate(values);
        }

        @Override
        public void onPostExecute(String result){
            Intent intent = new Intent(homeActivity.this, tem_humActivity.class);  //######################################
            intent.putExtra("temList", result);  //######################################
            homeActivity.this.startActivity(intent);
        }
    }

    class BackgroundTask_co2 extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            target = "https://akftmalffk.cafe24.com/co2_info.php";  //######################################
        }


        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target);  //해당 URL로 접속
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));  //하나씩 읽어올 수 있도록
                String temp;  //매 열마다 입력을 받도록 한다
                StringBuilder stringBuilder = new StringBuilder();  //각 결과값을 매 열마다 담겨 stringBuilder안에 넣음
                while((temp = bufferedReader.readLine()) != null)  //모든 열을 읽어옴
                {
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values){
            super.onProgressUpdate(values);
        }

        @Override
        public void onPostExecute(String result){
            Intent intent = new Intent(homeActivity.this, co2Activity.class);  //######################################
            intent.putExtra("co2List", result);  //######################################
            homeActivity.this.startActivity(intent);
        }
    }


}






