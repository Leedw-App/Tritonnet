package com.example.tton;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

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

public class tem_humActivity extends AppCompatActivity {

    private ListView listView;
    private TemListAdapter adapter;
    private List<Tem> temList;
    private LineChart chart;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tem_hum);

        /*  listView가 없을 때
        TextView powerListTextView = (TextView) findViewById(R.id.tem_humListTextView);
        Intent intent = getIntent();
        powerListTextView.setText(intent.getStringExtra("tem_humList"));
         */

        Intent intent = getIntent();

        listView = (ListView) findViewById(R.id.listView);
        chart = (LineChart) findViewById(R.id.chart);
        temList = new ArrayList<Tem>();
        adapter = new TemListAdapter(getApplicationContext(), temList);
        listView.setAdapter(adapter);

        try{
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("temList"));  //#######################
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;
            String temDate, temT, temH;   //#######################
            while(count < jsonArray.length())
            {
                JSONObject object = jsonArray.getJSONObject(count);
                temDate = object.getString("temDate");  //#######################
                temT = object.getString("temT");  //#######################
                temH = object.getString("temH");  //#######################
                Tem tem = new Tem(temDate, temT, temH);  //#######################
                temList.add(tem);  //#######################

                //#######################################
                ArrayList<Entry> values1 = new ArrayList<>();
                ArrayList<Entry> values2 = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++){
                    float val1 = (float) Float.parseFloat(jsonArray.getJSONObject(i).getString(("temT")));
                    float val2 = (float) Float.parseFloat(jsonArray.getJSONObject(i).getString(("temH")));
                    values1.add(new Entry(i, val1));
                    values2.add(new Entry(i, val2));
                }

                LineDataSet set1, set2;
                set1 = new LineDataSet(values1, "Tem");
                set2 = new LineDataSet(values2, "Hum");

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
