package com.example.tton;

public class Power {

    String powerDate;
    String powerkWh;

    public String getPowerDate() {
        return powerDate;
    }

    public void setPowerDate(String powerDate) {
        this.powerDate = powerDate;
    }

    public String getPowerkWh() {
        return powerkWh;
    }

    public void setPowerkWh(String powerkWh) {
        this.powerkWh = powerkWh;
    }

    public Power(String powerDate, String powerkWh) {
        this.powerDate = powerDate;
        this.powerkWh = powerkWh;
    }
/*
    public String getPowerDate(){return powerDate;}

    public void setPowerDate(String powerDate) {
        this.powerDate = powerDate;
    }

    public String getPowerkWh(){return powerkWh;}

    public void setPowerkWh(String powerkWh) {
        this.powerkWh = powerkWh;
    }

    public Power(String powerDate, String powerkWh){
        this.powerDate = powerDate;
        this.powerkWh = powerkWh;
    }
     */
}
