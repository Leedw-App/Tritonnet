package com.example.tton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);
        final Button loginButton = (Button) findViewById(R.id.loginButton);
        final CheckBox saveCheck = (CheckBox) findViewById(R.id.saveCheck);
        final CheckBox autoCheck = (CheckBox) findViewById(R.id.autoCheck);

        SharedPreferences sf = getSharedPreferences("loginId_File",MODE_PRIVATE);
        String loginId = sf.getString("loginId","");
        String loginPassword = sf.getString("loginPassword", "");

        //아아디 저장 체크 시
        if(!(loginId.equals("")))
            saveCheck.setChecked(true);

        idText.setText(loginId);

        //자동 로그인 체크 시
        if(!(loginPassword.equals("")))
            autoCheck.setChecked(true);

        passwordText.setText(loginPassword);

        if(!(loginPassword.getBytes().length == 0)) {

        }


        loginButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final String userID = idText.getText().toString();
                final String userPassword = passwordText.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response){
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                //String userID = jsonResponse.getString("userID");
                                //String userPassword = jsonResponse.getString("userPassword");
                                ////Intent intent = new Intent(LoginActivity.this, StartActivity.class);  //homeActivity#################################
                                //intent.putExtra("userID", userID);
                                //intent.putExtra("userPassword", userPassword);
                                ////LoginActivity.this.startActivity(intent);
                                new BackgroundTask_start().execute();  //homeActivity######################
                                Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("로그인에 실패하였습니다.")
                                        .setNegativeButton("다시 시도", null)
                                        .create()
                                        .show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(userID, userPassword, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        final CheckBox saveCheck = (CheckBox) findViewById(R.id.saveCheck);
        final CheckBox autoCheck = (CheckBox) findViewById(R.id.autoCheck);
        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);


        SharedPreferences sharedPreferences = getSharedPreferences("loginId_File", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(saveCheck.isChecked() && !(autoCheck.isChecked())) {  //아이디 저장에만 체크가 되어있으면 아이디만 저장
            String loginId = idText.getText().toString();
            //String loginPassword = passwordText.getText().toString();
            editor.putString("loginId", loginId);
            editor.putString("loginPassword", "");
        }else if(saveCheck.isChecked() && autoCheck.isChecked()) {  //자동 로그인과 아이디 저장 둘 다 체크가 되어있으면 아이디 비밀번호 저장
            //editor.putString("loginId","");
            String loginId = idText.getText().toString();
            String loginPassword = passwordText.getText().toString();
            editor.putString("loginId", loginId);
            editor.putString("loginPassword", loginPassword);
        }else if(autoCheck.isChecked() && !(saveCheck.isChecked())) {  //자동 로그인만 체크가 되어있으으면 아이디 비밀번호 저장
            String loginId = idText.getText().toString();
            String loginPassword = passwordText.getText().toString();
            editor.putString("loginId", loginId);
            editor.putString("loginPassword", loginPassword);
        }else{  //아무것도 체크되지 않으면 빈 값
            editor.putString("loginId","");
            editor.putString("loginPassword","");
        }
        editor.commit();
    }

    class BackgroundTask_start extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            target = "https://akftmalffk.cafe24.com/start.php";
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
            Intent intent = new Intent(LoginActivity.this, StartActivity.class);
            intent.putExtra("startList", result);
            LoginActivity.this.startActivity(intent);
        }
    }
}
