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

public class dustActivity extends AppCompatActivity {

    private ListView listView;
    private DustListAdapter adapter;
    private List<Dust> dustList;
    private LineChart chart;




    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dust);

        /*listView 없을 때
        TextView powerListTextView = (TextView) findViewById(R.id.dustListTextView);
        Intent intent = getIntent();
        powerListTextView.setText(intent.getStringExtra("dustList"));
         */

        Intent intent = getIntent();

        listView = (android.widget.ListView) findViewById(R.id.listView);
        chart = (LineChart) findViewById(R.id.chart);
        dustList = new ArrayList<Dust>();
        adapter = new DustListAdapter(getApplicationContext(), dustList);
        listView.setAdapter(adapter);

        try{
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("dustList"));  //#######################
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;
            String dustDate, dustPm2, dustPm10;   //#######################
            while(count < jsonArray.length())
            {
                JSONObject object = jsonArray.getJSONObject(count);
                dustDate = object.getString("dustDate");  //#######################
                dustPm2 = object.getString("dustPm2");  //#######################
                dustPm10 = object.getString("dustPm10");  //#######################
                Dust dust = new Dust(dustDate, dustPm2, dustPm10);  //#######################
                dustList.add(dust);  //#######################

                //#######################################
                ArrayList<Entry> values1 = new ArrayList<>();
                ArrayList<Entry> values2 = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++){
                    float val1 = (float) Float.parseFloat(jsonArray.getJSONObject(i).getString(("dustPm2")));
                    float val2 = (float) Float.parseFloat(jsonArray.getJSONObject(i).getString(("dustPm10")));
                    values1.add(new Entry(i, val1));
                    values2.add(new Entry(i, val2));
                }

                LineDataSet set1, set2;
                set1 = new LineDataSet(values1, "pm2");
                set2 = new LineDataSet(values2, "pm10");

                Legend legend = chart.getLegend();
                set1.setCircleRadius(3);
                set1.setDrawValues(false);
                legend.setTextColor(Color.WHITE);

                set2.setCircleRadius(3);
                set2.setDrawValues(false);



                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                dataSets.add(set1);
                dataSets.add(set2);


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
