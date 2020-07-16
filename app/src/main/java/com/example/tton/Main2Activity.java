package com.example.tton;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Main2Activity extends AppCompatActivity {
    TextView setid, setname;
    Button send, btn_out;
    EditText text_phone, text_sms;

    final int SEND_SMS_PERMISSION_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        setid = (TextView) findViewById(R.id.setid);
        setname = (TextView) findViewById(R.id.setname);

        btn_out = (Button) findViewById(R.id.btn_out);
        send = (Button) findViewById(R.id.btn_send);
        text_phone = (EditText) findViewById(R.id.et_number);
        text_sms = (EditText) findViewById(R.id.et_content);

        btn_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        send.setEnabled(false);
        if(checkPermission(Manifest.permission.SEND_SMS)){
            send.setEnabled(true);
        }else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},SEND_SMS_PERMISSION_REQUEST_CODE);
        }

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = text_phone.getText().toString();
                String SMS = text_sms.getText().toString();

                if(phoneNumber==null||phoneNumber.length()==0||SMS==null||SMS.length()==0){return;}
                if(checkPermission(Manifest.permission.SEND_SMS)){
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNumber, null, SMS, null, null);
                    Toast.makeText(Main2Activity.this, "SMS Success!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Main2Activity.this, "SMS failed, please try again later!", Toast.LENGTH_SHORT).show();
                }
/*
                try{
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNumber, null, SMS, null, null);
                    Toast.makeText(Main2Activity.this, "SMS Success!", Toast.LENGTH_SHORT).show();

                }catch(Exception e){
                    Toast.makeText(Main2Activity.this, "SMS failed, please try again later!", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
 */
            }
        });

        final CharSequence[] oAreas = {"T01-P3001-1234", "T01-S2001-1010", "T03-P1001-1000", "T03-P1001-1001"};

        //현장 ID 선택하기기
       setid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder oDialog = new AlertDialog.Builder(Main2Activity.this);

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

    }

    public boolean checkPermission(String permission) {
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }
}
