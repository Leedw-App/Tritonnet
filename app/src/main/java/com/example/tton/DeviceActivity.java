package com.example.tton;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DeviceActivity extends AppCompatActivity {


    private ListView listView;
    private DeviceListAdapter adapter;
    private List<Device> deviceList;
    private List<Device> saveList;                                           //###########EDIT


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);



        String device_id2 = ((StartActivity)StartActivity.mContext).device_id;              //StartActivity에서 불러온 변수 device_id
        String device_name2 = ((StartActivity)StartActivity.mContext).device_name;          //StartActivity에서 불러온 변수 device_name

        Intent intent = getIntent();
        listView = (ListView) findViewById(R.id.deviceListView);
        deviceList = new ArrayList<Device>();
        saveList = new ArrayList<Device>();                                  //###########EDIT
        adapter = new DeviceListAdapter(getApplicationContext(), deviceList, saveList);          //###########EDIT
        listView.setAdapter(adapter);

        try{
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("deviceList"));
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;
            String area_id, device_id, device_name, SPD, ELB, power, tem, hum, dust1, dust2, co2;
            while(count < jsonArray.length()){

                JSONObject object = jsonArray.getJSONObject(count);
                area_id = object.getString("area_id");
                device_id = object.getString("device_id");
                device_name = object.getString("device_name");
                SPD = object.getString("SPD");
                ELB = object.getString("ELB");
                power = object.getString("power");
                tem = object.getString("tem");
                hum = object.getString("hum");
                dust1 = object.getString("dust1");
                dust2 = object.getString("dust2");
                co2 = object.getString("co2");
                Device device = new Device(area_id, device_id, device_name, SPD, ELB, power, tem, hum, dust1, dust2, co2);
                deviceList.add(device);
                saveList.add(device);
                count++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        EditText search = (EditText) findViewById(R.id.deviceId);
        EditText device_name = (EditText) findViewById(R.id.devicename);
        search.setText(device_id2);
        device_name.setText(device_name2);
        //Toast.makeText(this, device_id2, Toast.LENGTH_LONG).show();
        if(search.length() != 0){
            searchDevice(search.getText().toString());
        }else{
            search.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    searchDevice(charSequence.toString());
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    //searchDevice(charSequence.toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(DeviceActivity.this, homeActivity.class);
                startActivity(intent);

            }
        });
    }

    public void searchDevice(String search){
        deviceList.clear();
        for(int i = 0; i < saveList.size(); i++){
            if(saveList.get(i).getDevice_id().contains(search)){
                deviceList.add(saveList.get(i));
            }
        }
        //adapter.notifyDataSetChanged();
    }




}
