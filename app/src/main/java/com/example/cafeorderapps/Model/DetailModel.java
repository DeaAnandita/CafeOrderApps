package com.example.cafeorderapps.Model;

public class DetailModel {
    String id, namaD, hargaD, jumlah;

    public DetailModel(String id, String namaD, String hargaD, String jumlah) {
        this.id = id;
        this.namaD = namaD;
        this.hargaD = hargaD;
        this.jumlah = jumlah;
    }

    public String getNamaD() {
        return namaD;
    }

    public void setNamaD(String namaD) {
        this.namaD = namaD;
    }

    public String getHargaD() {
        return hargaD;
    }

    public void setHargaD(String hargaD) {
        this.hargaD = hargaD;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

}
