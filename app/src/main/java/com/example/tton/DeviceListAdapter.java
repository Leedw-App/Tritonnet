package com.example.tton;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class DeviceListAdapter extends BaseAdapter {

    private Context context;
    private List<Device> deviceList;
    private List<Device> saveList;              //###########EDIT

    public DeviceListAdapter(Context context, List<Device> deviceList, List<Device> saveList) {    //###########EDIT
        this.context = context;
        this.deviceList = deviceList;
        this.saveList = saveList;                  //###########EDIT
    }


    @Override
    public int getCount() {
        return deviceList.size();
    }

    @Override
    public Object getItem(int i) {
        return deviceList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.device, null);
        TextView area_id = (TextView) v.findViewById(R.id.area_id);
        TextView device_id = (TextView) v.findViewById(R.id.device_id);
        TextView device_name = (TextView) v.findViewById(R.id.device_name);
        TextView SPD = (TextView) v.findViewById(R.id.deviceSPD);
        TextView ELB = (TextView) v.findViewById(R.id.deviceELB);
        TextView power = (TextView) v.findViewById(R.id.devicePower);
        TextView tem = (TextView) v.findViewById(R.id.deviceTem);
        TextView hum = (TextView) v.findViewById(R.id.deviceHum);
        TextView dust1 = (TextView) v.findViewById(R.id.deviceDust1);
        TextView dust2 = (TextView) v.findViewById(R.id.deviceDust2);
        TextView co2 = (TextView) v.findViewById(R.id.deviceCo2);

        area_id.setText(deviceList.get(i).getArea_id());
        device_id.setText(deviceList.get(i).getDevice_id());
        device_name.setText(deviceList.get(i).getDevice_name());
        SPD.setText(deviceList.get(i).getSPD());
        ELB.setText(deviceList.get(i).getELB());
        power.setText(deviceList.get(i).getPower());
        tem.setText(deviceList.get(i).getTem());
        hum.setText(deviceList.get(i).getHum());
        dust1.setText(deviceList.get(i).getDust1());
        dust2.setText(deviceList.get(i).getDust2());
        co2.setText(deviceList.get(i).getCo2());


        v.setTag(deviceList.get(i).getDevice_id());
        return v;
    }
}
