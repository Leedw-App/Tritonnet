package com.example.tton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

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

public class co2Activity extends AppCompatActivity {

    private ListView listView;
    private Co2ListAdapter adapter;
    private List<Co2> co2List;
    private LineChart chart;





    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_co2);

        /* listView가 없을 때
        TextView powerListTextView = (TextView) findViewById(R.id.co2ListTextView);
        Intent intent = getIntent();
        powerListTextView.setText(intent.getStringExtra("co2List"));
         */

        Intent intent = getIntent();

        listView = (ListView) findViewById(R.id.listView);
        chart = (LineChart) findViewById(R.id.chart);
        co2List = new ArrayList<Co2>();
        adapter = new Co2ListAdapter(getApplicationContext(), co2List);
        listView.setAdapter(adapter);


        try{
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("co2List"));  //#######################
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;
            String co2Date, co2Co2;   //#######################

            while(count < jsonArray.length()) {
                JSONObject object = jsonArray.getJSONObject(count);
                co2Date = object.getString("co2Date");  //#######################
                co2Co2 = object.getString("co2Co2");  //#######################
                Co2 co2 = new Co2(co2Date, co2Co2);  //#######################
                co2List.add(co2);  //#######################

//#######################################
                ArrayList<Entry> values = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++){
                    float val = (float) Float.parseFloat(jsonArray.getJSONObject(i).getString(("co2Co2")));
                    values.add(new Entry(i, val));
                }

                LineDataSet set1;
                set1 = new LineDataSet(values, "Co2");

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
