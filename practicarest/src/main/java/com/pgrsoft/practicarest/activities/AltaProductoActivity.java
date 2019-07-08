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
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.pgrsoft.practicarest.R;
import com.pgrsoft.practicarest.dateimpl.DateTypeDeserializer;
import com.pgrsoft.practicarest.interfaz.JsonPlaceHolderApi;
import com.pgrsoft.practicarest.model.Producto;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
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

        //final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy, HH/mm");
        // Creates the json object which manage the infprmation received
        GsonBuilder builder = new GsonBuilder();

        builder.registerTypeAdapter(Date.class, new JsonSerializer<Date>() {

            @Override
            public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
                long l = src.getTime();

                return new JsonPrimitive(l);
            }
        });

        Gson gson = builder.create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pedi-gest.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        createProducto();
    }

    private void createProducto(){

        int codigo = (int) (Math.random()*10000000);



        final Producto producto = new Producto("Yassa", 50, "Arroz", new Date(), true, "COMIDA");
        producto.setCodigo(codigo);

        Call<Producto> call = jsonPlaceHolderApi.createProducto(producto);

        call.enqueue(new Callback<Producto>() {

            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {

                if (!response.isSuccessful()){
                    textViewResult.setText("Code: "+response.code());
                    return;
                }
                int codigo = (int) Math.random() + 1000;

                Producto responseProducto = response.body();

                String content = "";

                content += "Nombre: " + producto.getNombre() + "\n";
                content += "Precio: " + producto.getPrecio() + "\n";
                content += "Descripcion: " + producto.getDescripcion() + "\n";
                content += "Fecha: " + producto.getFechaAlta() + "\n";
                content += "Descatalogado: " + producto.isDescatalogado() + "\n";
                content += "Categoria: " + producto.getCategoria() + "\n";

                textViewResult.append(content);

            }


            @Override
            public void onFailure(Call<Producto> call, Throwable t) {

                textViewResult.setText(t.getMessage());
            }
        });
    }


}
