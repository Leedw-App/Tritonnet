package com.example.tton;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class powerActivity extends AppCompatActivity {

    private ListView listView;
    private PowerListAdapter adapter;
    private List<Power> powerList;
    private LineChart chart;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power);

        /* ListView 없을 때
        TextView powerListTextView = (TextView) findViewById(R.id.powerListTextView);
        Intent intent = getIntent();
        powerListTextView.setText(intent.getStringExtra("powerList"));
        */

        Intent intent = getIntent();

        listView = (ListView) findViewById(R.id.listView);
        chart = (LineChart) findViewById(R.id.chart);
        powerList = new ArrayList<Power>();

        /*
        powerList.add(new Power("2020-06-02 13:00:00", "222.2"));
        powerList.add(new Power("2020-06-02 15:00:00", "333.2"));
         */

        adapter = new PowerListAdapter(getApplicationContext(), powerList);
        listView.setAdapter(adapter);


        try{
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("powerList"));  //#######################
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;
            String powerDate, powerkWh;
            while(count < jsonArray.length())
            {
                JSONObject object = jsonArray.getJSONObject(count);
                powerDate = object.getString("powerDate");
                powerkWh = object.getString("powerkWh");
                Power power = new Power(powerDate, powerkWh);
                powerList.add(power);

                //#######################################
                ArrayList<Entry> values = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++){
                    float val = (float) Float.parseFloat(jsonArray.getJSONObject(i).getString(("powerkWh")));
                    values.add(new Entry(i, val));
                }

                LineDataSet set1;
                set1 = new LineDataSet(values, "kWh");

                Legend legend = chart.getLegend();
                set1.setCircleRadius(3);
                set1.setDrawValues(false);
                legend.setTextColor(Color.WHITE);


                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                dataSets.add(set1);

                LineData data = new LineData(dataSets);

                set1.setColor(Color.RED);
                set1.setCircleColor(Color.BLUE);
                set1.setLineWidth(1);

                XAxis xAxis = chart.getXAxis();
                xAxis.setEnabled(false);
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setTextColor(Color.WHITE);

                YAxis yLAxis = chart.getAxisLeft();
                yLAxis.setTextColor(Color.WHITE);

                YAxis yRAxis = chart.getAxisRight();
                yRAxis.setDrawLabels(false);
                yRAxis.setDrawAxisLine(false);
                yRAxis.setDrawGridLines(false);

                Description description = new Description();
                description.setText("");

                chart.setDoubleTapToZoomEnabled(false);
                chart.setDrawGridBackground(false);
                chart.setDescription(description);


                chart.setData(data);
//#######################################

                count++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }




    }
}
