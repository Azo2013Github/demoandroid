package com.pgrsoft.practicarest.interfaz;


import com.pgrsoft.practicarest.model.Camarero;
import com.pgrsoft.practicarest.model.Pedido;
import com.pgrsoft.practicarest.model.Producto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("camareros")
    Call<List<Camarero>> getCamareros();

    @GET("productos")
    Call<List<Producto>> getProductos();

    @GET("pedidos")
    Call<List<Pedido>> getPedidos();




}
