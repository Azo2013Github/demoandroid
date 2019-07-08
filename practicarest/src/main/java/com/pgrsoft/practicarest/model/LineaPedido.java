package com.pgrsoft.practicarest.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LineaPedido {


    private int cantidad;
    private double precio;

   // @SerializedName("name")
    private Producto producto;

    public LineaPedido() {
    }

    public int getCantidad() {
        return cantidad;
    }

    public LineaPedido(int cantidad, double precio, Producto producto) {
        this.cantidad = cantidad;
        this.precio = precio;
        this.producto = producto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
