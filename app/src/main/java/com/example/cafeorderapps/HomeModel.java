package com.example.cafeorderapps;

public class HomeModel {

    String id, email, nama;

    public HomeModel(String id, String email, String nama) {
        this.id = id;
        this.email = email;
        this.nama = nama;
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
}
