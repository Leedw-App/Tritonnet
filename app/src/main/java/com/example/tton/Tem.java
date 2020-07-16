package com.example.tton;

public class Tem {

    String temDate;
    String temT;
    String temH;

    public String getTemDate() {
        return temDate;
    }

    public void setTemDate(String temDate) {
        this.temDate = temDate;
    }

    public String getTemT() {
        return temT;
    }

    public void setTemT(String temT) {
        this.temT = temT;
    }

    public String getTemH() {
        return temH;
    }

    public void setTemH(String temH) {
        this.temH = temH;
    }

    public Tem(String temDate, String temT, String temH) {
        this.temDate = temDate;
        this.temT = temT;
        this.temH = temH;
    }
}
