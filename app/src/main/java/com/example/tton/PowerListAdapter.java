package com.example.tton;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class PowerListAdapter extends BaseAdapter {

    private Context context;
    private List<Power> powerList;

    public PowerListAdapter(Context context, List<Power> powerList){
        this.context = context;
        this.powerList = powerList;
    }


    @Override
    public int getCount() {
        return powerList.size();
    }

    @Override
    public Object getItem(int i) {
        return powerList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.power, null);
        TextView powerDate = (TextView) v.findViewById(R.id.powerDate);
        TextView powerkWh = (TextView) v.findViewById(R.id.powerkWh);

        powerDate.setText(powerList.get(i).getPowerDate());
        powerkWh.setText(powerList.get(i).getPowerkWh());

        v.setTag(powerList.get(i).getPowerDate());
        return v;
    }
}
