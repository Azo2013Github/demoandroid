package com.pgrsoft.practicarest.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.pgrsoft.practicarest.R;
import com.pgrsoft.practicarest.interfaz.JsonPlaceHolderApi;
import com.pgrsoft.practicarest.model.Producto;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AltaProductoActivity extends AppCompatActivity {

    private TextView textViewResult;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_producto);

        textViewResult = (TextView) findViewById(R.id.idAltaProducto);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pedi-gest.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        createProducto();
    }

    private void createProducto(){

        Producto producto = new Producto("Yassa", 50, "Arroz", new Date(), true, "Comida segunda plato");

        Call<Producto> call = jsonPlaceHolderApi.createProducto(producto);

        call.enqueue(new Callback<Producto>() {

            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {

                if (!response.isSuccessful()){
                    textViewResult.setText("Code: "+response.code());
                    return;
                }

                Producto responseProducto = response.body();

                String content = "";

                content += "Nombre: " + responseProducto.getNombre() + "\n";
                content += "Precio: " + responseProducto.getPrecio() + "\n";
                content += "Descripcion: " + responseProducto.getDescripcion() + "\n";
                content += "Fecha: " + responseProducto.getFechaAlta() + "\n";
                content += "Descatalogado: " + responseProducto.isDescatalogado() + "\n";
                content += "Categoria: " + responseProducto.getCategoria() + "\n";

                textViewResult.append(content);

            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {

                textViewResult.setText(t.getMessage());
            }
        });
    }


}
