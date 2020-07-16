package com.example.tton;

public class Device {
    String area_id;
    String device_id;
    String device_name;
    String SPD;
    String ELB;
    String power;
    String tem;
    String hum;
    String dust1;
    String dust2;
    String co2;

    public String getArea_id() {return area_id;}
    public void setArea_id(String area_id) {this.area_id = area_id;}

    public String getDevice_id() {return device_id;}
    public void setDevice_id(String device_id) {this.device_id = device_id;}

    public String getDevice_name() {return device_name;}
    public void setDevice_name(String device_name) {this.device_name = device_name;}

    public String getSPD() {return SPD;}
    public void setSPD(String SPD) {this.SPD = SPD;}

    public String getELB() {return ELB;}
    public void setELB(String ELB) {this.ELB = ELB;}

    public String getPower() {return power;}
    public void setPower(String power) {this.power = power;}

    public String getTem() {return tem;}
    public void setTem(String tem) {this.tem = tem;}

    public String getHum() {return hum;}
    public void setHum(String hum) {this.hum = hum;}

    public String getDust1() {return dust1;}
    public void setDust1(String dust1) {this.dust1 = dust1;}

    public String getDust2() {return dust2;}
    public void setDust2(String dust2) {this.dust2 = dust2;}

    public String getCo2() {return co2;}
    public void setCo2(String co2) {this.co2 = co2;}

    public Device(String area_id, String device_id, String device_name, String SPD, String ELB, String power, String tem, String hum, String dust1, String dust2, String co2){
        this.area_id = area_id;
        this.device_id = device_id;
        this.device_name = device_name;
        this.SPD = SPD;
        this.ELB = ELB;
        this.power = power;
        this.tem = tem;
        this.hum = hum;
        this.dust1 = dust1;
        this.dust2 = dust2;
        this.co2 = co2;
    }


}
