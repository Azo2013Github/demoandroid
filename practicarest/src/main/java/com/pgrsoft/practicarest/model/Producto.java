package com.pgrsoft.practicarest.model;

import java.util.Date;

public class Producto {

    private String categoria;
    private long codigo;
    private boolean descatalogado;
    private String descripcion;
    private Date fechaAlta;
    private String nombre;
    private double precio;

    public Producto() {
    }

    public Producto(String nombre, double precio, String descripcion, Date fechaAlta, boolean descatalogado,String categoria) {
        this.categoria = categoria;
        this.descatalogado = descatalogado;
        this.descripcion = descripcion;
        this.fechaAlta = fechaAlta;
        this.nombre = nombre;
        this.precio = precio;

        /*"nombre": "café con leche",
    "precio": 1.4,
    "descripcion": "Descripción Producto B",
    "fechaAlta": 1445299200000,
    "descatalogado": false,
    "categoria": "CAFE"*/
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public boolean isDescatalogado() {
        return descatalogado;
    }

    public void setDescatalogado(boolean descatalogado) {
        this.descatalogado = descatalogado;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", descripcion='" + descripcion + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", descatalogado=" + descatalogado +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
