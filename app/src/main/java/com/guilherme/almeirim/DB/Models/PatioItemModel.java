package com.guilherme.almeirim.DB.Models;

import java.text.SimpleDateFormat;

/**
 * Created by Guilherme on 11/09/2016.
 */
public class PatioItemModel {
    private int id;
    private float comprimento1;
    private float comprimento2;
    private float rodo;
    private float diametro1;
    private float diametro2;
    private float oco;
    private int patioId;
    private int arvoreId;

    private SimpleDateFormat iso8601Format = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getComprimento1() {
        return comprimento1;
    }

    public void setComprimento1(float comprimento1) {
        this.comprimento1 = comprimento1;
    }

    public float getComprimento2() {
        return comprimento2;
    }

    public void setComprimento2(float comprimento2) {
        this.comprimento2 = comprimento2;
    }

    public float getRodo() {
        return rodo;
    }

    public void setRodo(float rodo) {
        this.rodo = rodo;
    }

    public float getDiametro1() {
        return diametro1;
    }

    public void setDiametro1(float diametro1) {
        this.diametro1 = diametro1;
    }

    public float getDiametro2() {
        return diametro2;
    }

    public void setDiametro2(float diametro2) {
        this.diametro2 = diametro2;
    }

    public float getOco() {
        return oco;
    }

    public void setOco(float oco) {
        this.oco = oco;
    }

    public int getPatioId() {
        return patioId;
    }

    public void setPatioId(int patioId) {
        this.patioId = patioId;
    }

    public int getArvoreId() {
        return arvoreId;
    }

    public void setArvoreId(int arvoreId) {
        this.arvoreId = arvoreId;
    }
}


