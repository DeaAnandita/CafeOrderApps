package com.example.cafeorderapps.Model;

public class HomeModel {

    String id, email, nama;
    boolean doubleClick;

    public HomeModel(String id, String email, String nama, boolean doubleClick) {
        this.id = id;
        this.email = email;
        this.nama = nama;
        this.doubleClick = doubleClick;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public boolean isDoubleClick() {
        return doubleClick;
    }

    public void setDoubleClick(boolean doubleClick) {
        this.doubleClick = doubleClick;
    }
}
