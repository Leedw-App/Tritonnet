package com.example.tton;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class TemListAdapter extends BaseAdapter {

    private Context context;
    private List<Tem> temList;

    public TemListAdapter(Context context, List<Tem> temList){
        this.context = context;
        this.temList = temList;
    }

    @Override
    public int getCount() {
        return temList.size();
    }

    @Override
    public Object getItem(int i) {
        return temList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context,R.layout.tem, null);
        TextView temDate = (TextView) v.findViewById(R.id.temDate);
        TextView temT = (TextView) v.findViewById(R.id.temT);
        TextView temH = (TextView) v.findViewById(R.id.temH);

        temDate.setText(temList.get(i).getTemDate());
        temT.setText(temList.get(i).getTemT());
        temH.setText(temList.get(i).getTemH());

        v.setTag(temList.get(i).getTemDate());
        return v;
    }
}
