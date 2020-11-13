package com.example.cafeorderapps;

public class CategoriModel {
    String id, txtNamaCatg;

    public CategoriModel(String id, String txtNamaCatg) {
        this.id = id;
        this.txtNamaCatg = txtNamaCatg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTxtNamaCatg() {
        return txtNamaCatg;
    }

    public void setTxtNamaCatg(String txtNamaCatg) {
        this.txtNamaCatg = txtNamaCatg;
    }


}
