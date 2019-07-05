package com.pgrsoft.restpractica.model;

import java.util.Date;

public class Pedido {

    private long id;
    private Date date;
    private int mesa;
    private Camarero camarero;

    public Pedido() {
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
