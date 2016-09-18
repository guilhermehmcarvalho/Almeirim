package com.guilherme.almeirim.DB.Models;

/**
 * Created by Guilherme on 11/09/2016.
 */
public class TransporteModel {
    private String cliente;
    private String motorista;
    private String placa;
    private int idRomaneio;
    private int id;
    private int projectId;

    @Override
    public String toString() {
        return cliente + "/" + Integer.toString(idRomaneio);
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getMotorista() {
        return motorista;
    }

    public void setMotorista(String motorista) {
        this.motorista = motorista;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getIdRomaneio() {
        return idRomaneio;
    }

    public void setIdRomaneio(int idRomaneio) {
        this.idRomaneio = idRomaneio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
