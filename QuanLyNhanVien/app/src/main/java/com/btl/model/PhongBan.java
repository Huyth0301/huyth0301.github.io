package com.btl.model;

import java.io.Serializable;

public class PhongBan implements Serializable {
    private int idPB;
    private String TenPB;
    private String DiaDiem;

    public PhongBan(int idPB, String tenPB, String diaDiem) {
        this.idPB = idPB;
        TenPB = tenPB;
        DiaDiem = diaDiem;
    }

    public PhongBan() {
    }

    public int getIdPB() {
        return idPB;
    }

    public void setIdPB(int idPB) {
        this.idPB = idPB;
    }

    public String getTenPB() {
        return TenPB;
    }

    public void setTenPB(String tenPB) {
        TenPB = tenPB;
    }

    public String getDiaDiem() {
        return DiaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        DiaDiem = diaDiem;
    }
}
