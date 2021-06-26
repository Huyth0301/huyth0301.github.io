package com.btl.model;

import java.io.Serializable;

public class Account implements Serializable {
    private int idnv;
    private String user;
    private String password;

    public Account(int idnv, String user, String password) {
        this.idnv = idnv;
        this.user = user;
        this.password = password;
    }

    public Account(String user, String password) {
        this.idnv =1;
        this.user = user;
        this.password = password;
    }

    public Account() {
    }

    public int getIdnv() {
        return idnv;
    }

    public void setIdnv(int idnv) {
        this.idnv = idnv;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
