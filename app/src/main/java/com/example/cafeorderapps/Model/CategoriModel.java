package com.example.cafeorderapps.Model;

public class CategoriModel {
    String id, txtNamaCatg;
    boolean doubleClick;

    public CategoriModel(String id, String txtNamaCatg, boolean doubleClick) {
        this.id = id;
        this.txtNamaCatg = txtNamaCatg;
        this.doubleClick = doubleClick;
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

    public boolean isDoubleClick() {
        return doubleClick;
    }

    public void setDoubleClick(boolean doubleClick) {
        this.doubleClick = doubleClick;
    }
}
