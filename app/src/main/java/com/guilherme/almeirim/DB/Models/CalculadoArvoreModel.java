package com.guilherme.almeirim.DB.Models;

/**
 * Created by Guilherme on 11/09/2016.
 */
public class CalculadoArvoreModel {
    private float geoBruto;
    private float geoLiquido;
    private float franconBruto;
    private float franconLiquido;
    private float oco;
    private int idArvore;

    public float getGeoBruto() {
        return geoBruto;
    }

    public void setGeoBruto(float geoBruto) {
        this.geoBruto = geoBruto;
    }

    public float getGeoLiquido() {
        return geoLiquido;
    }

    public void setGeoLiquido(float geoLiquido) {
        this.geoLiquido = geoLiquido;
    }

    public float getFranconBruto() {
        return franconBruto;
    }

    public void setFranconBruto(float franconBruto) {
        this.franconBruto = franconBruto;
    }

    public float getFranconLiquido() {
        return franconLiquido;
    }

    public void setFranconLiquido(float franconLiquido) {
        this.franconLiquido = franconLiquido;
    }

    public float getOco() {
        return oco;
    }

    public void setOco(float oco) {
        this.oco = oco;
    }

    public int getIdArvore() {
        return idArvore;
    }

    public void setIdArvore(int idArvore) {
        this.idArvore = idArvore;
    }
}
