package com.pgrsoft.practicarest.model;

import java.util.Date;
import java.util.List;

public class Pedido {

    private long id;
    private Date date;
    private int mesa;
    private Camarero camarero;
    private List<LineaPedido> lineasPedido;

    public Pedido() {
    }

    public List<LineaPedido> getLineasPedido() {
        return lineasPedido;
    }

    public void setLineasPedido(List<LineaPedido> lineasPedido) {
        this.lineasPedido = lineasPedido;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public Camarero getCamarero() {
        return camarero;
    }

    public void setCamarero(Camarero camarero) {
        this.camarero = camarero;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", date=" + date +
                ", mesa=" + mesa +
                ", camarero=" + camarero +
                '}';
    }
}
