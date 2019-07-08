package com.pgrsoft.practicarest.model;

import com.google.gson.annotations.SerializedName;

public class Camarero {

    private long codigo;

    //@SerializedName("body")
    private String nombre;

    public Camarero() {
    }

    public Camarero(long codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Camarero{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
