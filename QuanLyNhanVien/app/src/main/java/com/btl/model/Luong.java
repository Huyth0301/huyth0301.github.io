package com.btl.model;

import java.io.Serializable;

public class Luong implements Serializable {
    public int getIdBacLuong() {
        return idBacLuong;
    }

    public void setIdBacLuong(int idBacLuong) {
        this.idBacLuong = idBacLuong;
    }

    public float getLuongCoBan() {
        return LuongCoBan;
    }

    public void setLuongCoBan(float luongCoBan) {
        LuongCoBan = luongCoBan;
    }

    public float getHeSoLuong() {
        return HeSoLuong;
    }

    public void setHeSoLuong(float heSoLuong) {
        HeSoLuong = heSoLuong;
    }

    public float getHeSoPhuCap() {
        return HeSoPhuCap;
    }

    public void setHeSoPhuCap(float heSoPhuCap) {
        HeSoPhuCap = heSoPhuCap;
    }

    private int idBacLuong;
    private float LuongCoBan;
    private float HeSoLuong;
    private float HeSoPhuCap;

    public Luong(int idBacLuong,float luongCoBan, float heSoLuong, float heSoPhuCap) {
        this.idBacLuong=idBacLuong;
        LuongCoBan = luongCoBan;
        HeSoLuong = heSoLuong;
        HeSoPhuCap = heSoPhuCap;
    }
    public Luong() {

    }

}
