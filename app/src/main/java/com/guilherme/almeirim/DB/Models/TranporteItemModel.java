package com.guilherme.almeirim.DB.Models;

/**
 * Created by Guilherme on 16/09/2016.
 */
public class TranporteItemModel {
    private int id;
    private int arvoreId;
    private int transporteId;

    public int getArvoreId() {
        return arvoreId;
    }

    public void setArvoreId(int arvoreId) {
        this.arvoreId = arvoreId;
    }

    public int getTransporteId() {
        return transporteId;
    }

    public void setTransporteId(int transporteId) {
        this.transporteId = transporteId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
