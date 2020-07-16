package com.example.tton;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class StartListAdapter extends BaseAdapter {

    private Context context;
    private List<Start> startList;
    private List<Start> saveList;              //###########EDIT

    public StartListAdapter(Context context, List<Start> startList, List<Start> saveList) {    //###########EDIT
        this.context = context;
        this.startList = startList;
        this.saveList = saveList;                  //###########EDIT
    }


    @Override
    public int getCount() {
        return startList.size();
    }

    @Override
    public Object getItem(int i) {
        return startList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.start, null);
        TextView area_id = (TextView) v.findViewById(R.id.area_id);
        TextView device_name = (TextView) v.findViewById(R.id.device_name);
        TextView SPD = (TextView) v.findViewById(R.id.deviceSPD);
        TextView power = (TextView) v.findViewById(R.id.devicePower);


        area_id.setText(startList.get(i).getArea_id());
        device_name.setText(startList.get(i).getDevice_name());
        SPD.setText(startList.get(i).getSPD());
        if(startList.get(i).getPower() >= 135 && startList.get(i).getPower() < 200) {
            power.setBackgroundColor(Color.parseColor("#f5f507"));
        }else if(startList.get(i).getPower() > 200){
            power.setBackgroundColor(Color.parseColor("#ff2424"));
        }else if(startList.get(i).getPower() < 135){
            power.setBackgroundColor(Color.parseColor("#00f068"));
        }


        v.setTag(startList.get(i).getArea_id());
        return v;
    }



}
