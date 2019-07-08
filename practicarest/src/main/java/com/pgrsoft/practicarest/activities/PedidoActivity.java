package com.pgrsoft.practicarest.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.pgrsoft.practicarest.R;
import com.pgrsoft.practicarest.dateimpl.DateTypeDeserializer;
import com.pgrsoft.practicarest.interfaz.JsonPlaceHolderApi;
import com.pgrsoft.practicarest.model.Camarero;
import com.pgrsoft.practicarest.model.LineaPedido;
import com.pgrsoft.practicarest.model.Pedido;
import com.pgrsoft.practicarest.model.Producto;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PedidoActivity extends AppCompatActivity {

    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private TextView textViewResult;

    private List<LineaPedido> lineasPedidos;

    private Producto producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        textViewResult = (TextView) findViewById(R.id.idResultado);
        lineasPedidos = new ArrayList<>();

        producto = new Producto();


        // Creates the json object which will manage the information received
        GsonBuilder builder = new GsonBuilder();

        // Register an adapter to manage the date types as long values
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {

            @Override
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                Log.d("** getAsJsonPrimitive:", json.getAsJsonPrimitive().toString());
                long millisecons = json.getAsJsonPrimitive().getAsLong();
                return new Date(millisecons);
            }
        });

        //Instanciamos el Gson para pasarle
        Gson gson = builder.create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pedi-gest.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create(gson)) //al retrofit y convrtir la fecha Gson en java
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        getPedidos();
    }

    private void getPedidos(){

        Call<List<Pedido>> call = jsonPlaceHolderApi.getPedidos();


        call.enqueue(new Callback<List<Pedido>>() {

            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {
                if (!response.isSuccessful()){
                    textViewResult.setText("CODE: "+response.code());
                    return;
                }

                String content = "";

                List<Pedido> pedidos = response.body();

                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapter(Date.class, new DateTypeDeserializer());

                for (Pedido pedido: pedidos) {
                    content += "ID: " + pedido.getId() + "\n";
                    content += "FECHA: " + pedido.getDate() + "\n";
                    content += "MESA: " +pedido.getMesa()+ "\n";
                    content += "ID CAMARERO: " + pedido.getCamarero().getCodigo() + "\n";
                    content += "NOMBRE CAMARERO: " + pedido.getCamarero().getNombre() + "\n";

                    lineasPedidos = pedido.getLineasPedido();

                    for (LineaPedido lineaPedido : lineasPedidos) {
                        content += "CANTIDAD: " + lineaPedido.getCantidad() + "\n";
                        content += "PRECIO: " + lineaPedido.getPrecio() + "\n";

                        producto = lineaPedido.getProducto();

                        content += "CODIGO PRODUCTO: " + producto.getCodigo() + "\n";
                        content += "NOMBRE PRODUCTO: " + producto.getNombre() + "\n";
                        content += "PRECIO PRODUC: " + producto.getPrecio() + "\n";
                        content += "DESCRIPCION PRODUC: " + producto.getDescripcion() + "\n";
                        content += "FECHA_ALTA PRODUC: " + producto.getFechaAlta() + "\n";
                        content += "DESCATALOGADO: " + producto.isDescatalogado() + "\n";
                        content += "CATEGORIA: " + producto.getCategoria() + "\n";
                    }

                   textViewResult.append(content);

                }

            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {
                textViewResult.setText("MESSAGE: " + t.getMessage());

            }
        });


    }


}
