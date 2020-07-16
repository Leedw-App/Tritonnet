package com.example.tton;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Co2ListAdapter extends BaseAdapter {

    private Context context;
    private List<Co2> co2List;

    public Co2ListAdapter(Context context, List<Co2> co2List){
        this.context = context;
        this.co2List = co2List;
    }

    @Override
    public int getCount() {
        return co2List.size();
    }

    @Override
    public Object getItem(int i) {
        return co2List.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.co2, null);
        TextView co2Date = (TextView) v.findViewById(R.id.co2Date);
        TextView co2Co2 = (TextView) v.findViewById(R.id.co2Co2);

        co2Date.setText(co2List.get(i).getCo2Date());
        co2Co2.setText(co2List.get(i).getCo2Co2());

        v.setTag(co2List.get(i).getCo2Date());
        return v;
    }
}
