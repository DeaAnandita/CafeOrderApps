package com.example.cafeorderapps.Model;

public class DetailModel {
    String id, namaD, hargaD;

    public DetailModel(String id, String namaD, String hargaD) {
        this.id = id;
        this.namaD = namaD;
        this.hargaD = hargaD;

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


}
