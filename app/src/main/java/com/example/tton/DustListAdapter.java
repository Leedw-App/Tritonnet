package com.example.tton;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class DustListAdapter extends BaseAdapter {

    private Context context;
    private List<Dust> dustList;

    public DustListAdapter(Context context, List<Dust> dustList){
        this.context = context;
        this.dustList = dustList;
    }

    @Override
    public int getCount() {
        return dustList.size();
    }

    @Override
    public Object getItem(int i) {
        return dustList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = View.inflate(context, R.layout.dust, null);
        TextView dustDate = (TextView) v.findViewById(R.id.dustDate);
        TextView dustPm2 = (TextView) v.findViewById(R.id.dustPm2);
        TextView dustPm10 = (TextView) v.findViewById(R.id.dustPm10);


        dustDate.setText(dustList.get(i).getDustDate());
        dustPm2.setText(dustList.get(i).getDustPm2());
        dustPm10.setText(dustList.get(i).getDustPm10());


        v.setTag(dustList.get(i).getDustDate());
        return v;

    }
}
