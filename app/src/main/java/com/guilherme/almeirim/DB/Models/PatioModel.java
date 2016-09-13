package com.guilherme.almeirim.DB.Models;

/**
 * Created by Guilherme on 11/09/2016.
 */
public class PatioModel {
    private String dataDigitacao;
    private int numRomaneio;
    private String funcionario;
    private int projectId;
    private int patioId;

    @Override
    public String toString() {
        return Integer.toString(numRomaneio);
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }

    public String getDataDigitacao() {
        return dataDigitacao;
    }

    public void setDataDigitacao(String dataDigitacao) {
        this.dataDigitacao = dataDigitacao;
    }

    public int getNumRomaneio() {
        return numRomaneio;
    }

    public void setNumRomaneio(int numRomaneio) {
        this.numRomaneio = numRomaneio;
    }

    public int getPatioId() {
        return patioId;
    }

    public void setPatioId(int patioId) {
        this.patioId = patioId;
    }
}
