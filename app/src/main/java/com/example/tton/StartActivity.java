package com.example.tton;



import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity {



    TextView setid, setname, choice_id;
    private ListView listView;
    private StartListAdapter adapter;
    private List<Start> startList;
    private List<Start> saveList;

    public static Context mContext;
    public String device_id;
    public String device_name;

    //Button btn_01;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        setid = (TextView) findViewById(R.id.setid);
        setname = (TextView) findViewById(R.id.setname);


        //설정 버튼 클릭
        ImageButton btn_set = (ImageButton) findViewById(R.id.btn_set);
        btn_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });



        //리스트 목록
        final CharSequence[] oAreas = {"T01-P3001-1234", "T01-S2001-1010", "T03-P1001-1000", "T03-P1001-1001"};

        //현장 ID 선택하기기
        setid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder oDialog = new AlertDialog.Builder(StartActivity.this);

                oDialog.setTitle("현장 ID를 선택하세요").setItems(oAreas, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setid.setText(oAreas[which]);
                        String idText = setid.getText().toString();
                        //Toast.makeText(Main2Activity.this, idText, Toast.LENGTH_SHORT).show();
                        if(idText.equals("T01-P3001-1234")){
                            setname.setText("인천");
                        }else if(idText.equals("T01-S2001-1010")){
                            setname.setText("서울");
                        }else if(idText.equals("T03-P1001-1000")){
                            setname.setText("대구");
                        }else if(idText.equals("T03-P1001-1001")){
                            setname.setText("대전");
                        }
                    }
                })
                        .setCancelable(false)
                        .show();
            }
        });

        Intent intent = getIntent();
        listView = (ListView) findViewById(R.id.startListView);
        startList = new ArrayList<Start>();
        saveList = new ArrayList<Start>();                                  //###########EDIT
        adapter = new StartListAdapter(getApplicationContext(), startList, saveList);          //###########EDIT
        listView.setAdapter(adapter);

        choice_id = (TextView) findViewById(R.id.choice_id);

        try{
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("startList"));
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;
            String area_id, device_id, device_name, SPD;
            int power;
            while(count < jsonArray.length()){

                JSONObject object = jsonArray.getJSONObject(count);
                area_id = object.getString("area_id");
                device_id = object.getString("device_id");
                device_name = object.getString("device_name");
                SPD = object.getString("SPD");
                power = object.getInt("power");


                Start start = new Start(area_id, device_id, device_name, SPD, power);
                startList.add(start);
                saveList.add(start);
                count++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        setid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                searchStart(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                new BackgroundTask_device().execute();
                device_id = saveList.get(position).getDevice_id();
                device_name = saveList.get(position).getDevice_name();

            }
        });

        mContext = this;
    }

    class BackgroundTask_device extends AsyncTask<Void, Void, String>{
        String target;

        @Override
        protected void onPreExecute() {
            target = "https://akftmalffk.cafe24.com/device.php";
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

            Intent intent = new Intent(StartActivity.this, DeviceActivity.class);
            intent.putExtra("deviceList", result);
            startActivity(intent);
        }
    }

    public void searchStart(String search){
        startList.clear();
        for(int i = 0; i < saveList.size(); i++){
            if(saveList.get(i).getArea_id().contains(search)){
                startList.add(saveList.get(i));
            }
        }
        adapter.notifyDataSetChanged();
    }


}

