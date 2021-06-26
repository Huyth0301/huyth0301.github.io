package com.btl.model;


import java.util.Date;

public class ChamCong {
    private int IDCC;
    private  int IDNV;
    private String date;

    public ChamCong(int IDCC, int IDNV, String date) {
        this.IDCC = IDCC;
        this.IDNV = IDNV;
        this.date = date;
    }
    public ChamCong(){

    }
    public int getIDCC() {
        return IDCC;
    }

    public void setIDCC(int IDCC) {
        this.IDCC = IDCC;
    }

    public int getIDNV() {
        return IDNV;
    }

    public void setIDNV(int IDNV) {
        this.IDNV = IDNV;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
