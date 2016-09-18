package com.guilherme.almeirim.DB.Models;

/**
 * Created by Guilherme on 11/09/2016.
 */
public class ArvoreModel {
    private int id;
    private int placa;
    private int UT;
    private int cap;
    private String especie;
    private int idProjeto;

    @Override
    public String toString() {
        return placa + " " + UT + " " + especie + " " + cap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlaca() {
        return placa;
    }

    public void setPlaca(int placa) {
        this.placa = placa;
    }

    public int getUT() {
        return UT;
    }

    public void setUT(int UT) {
        this.UT = UT;
    }

    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }
}
