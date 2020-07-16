package com.example.tton;

public class Dust {

    String dustDate;
    String dustPm2;
    String dustPm10;


    public String getDustDate() {
        return dustDate;
    }

    public void setDustDate(String dustDate) {
        this.dustDate = dustDate;
    }

    public String getDustPm2() {
        return dustPm2;
    }

    public void setDustPm2(String dustPm2) {
        this.dustPm2 = dustPm2;
    }

    public String getDustPm10() {
        return dustPm10;
    }

    public void setDustPm10(String dustPm10) {
        this.dustPm10 = dustPm10;
    }

    public Dust(String dustDate, String dustPm2, String dustPm10) {
        this.dustDate = dustDate;
        this.dustPm2 = dustPm2;
        this.dustPm10 = dustPm10;
    }
}
