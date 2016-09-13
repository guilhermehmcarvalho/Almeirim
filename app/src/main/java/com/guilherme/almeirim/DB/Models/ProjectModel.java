package com.guilherme.almeirim.DB.Models;

/**
 * Created by Guilherme on 11/09/2016.
 */
public class ProjectModel {

    private String name;
    private int id;

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
