package com.btl.model;

import java.io.Serializable;

public class Employee implements Serializable {
    int id;
    String name ;
    String GioiTinh;
    String Addr;
    String sdt;
    String ChucVu;
    int IDPB;
    int id_bacluong;

    public Employee() {

    }

    public Employee(int id, String name, String GioiTinh, String addr, String sdt, String chucVu, int pb, int id_bl) {
        super();
        this.id = id;
        this.name = name;
        this.GioiTinh=GioiTinh;
        this.Addr = addr;
        this.sdt = sdt;
        this.ChucVu = chucVu;
        this.IDPB = pb;
        this.id_bacluong  = id_bl;

    }

    public int getId_bacluong() {
        return id_bacluong;
    }

    public void setId_bacluong(int id_bacluong) {
        this.id_bacluong = id_bacluong;
    }
    public void setIDPB(int IDPB) {
        this.IDPB = IDPB;
    }
    public int getIDPB() {
        return IDPB;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return Addr;
    }

    public void setAddr(String addr) {
        Addr = addr;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String chucVu) {
        ChucVu = chucVu;
    }
}
