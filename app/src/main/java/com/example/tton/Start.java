package com.example.tton;

public class Start {

    String area_id;
    String device_id;
    String device_name;
    String SPD;
    int power;
    //String power;

    public String getArea_id() {return area_id;}
    public void setArea_id(String area_id) {this.area_id = area_id;}

    public String getDevice_id() {return device_id;}
    public void setDevice_id(String device_id) {this.device_id = device_id;}

    public String getDevice_name() {return device_name;}
    public void setDevice_name(String device_name) {this.device_name = device_name;}

    public String getSPD() {return SPD;}
    public void setSPD(String SPD) {this.SPD = SPD;}

    public int getPower() {return power;}/////////////////////////////////////////////////////
    public void setPower(int power) {this.power = power;}/////////////////////////////////////////////////////

    public Start(String area_id, String device_id, String device_name, String SPD, int power){/////////////////////////////////////////////////////
        this.area_id = area_id;
        this.device_id = device_id;
        this.device_name = device_name;
        this.SPD = SPD;
        this.power = power;

    }

}
