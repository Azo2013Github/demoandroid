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
import com.pgrsoft.practicarest.model.Producto;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductoActivity extends AppCompatActivity {

    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private TextView textViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);


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


        Gson gson = builder.create();

        textViewResult = (TextView) findViewById(R.id.idProducto);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pedi-gest.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);


        //createProducto();
        getProductos();
    }

    private void getProductos(){

        Call<List<Producto>> call = jsonPlaceHolderApi.getProductos();

        call.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {

                List<Producto> productos = response.body();

                /*Conversion date json to date java: */
                //GsonBuilder gsonBuilder = new GsonBuilder();
                //gsonBuilder.registerTypeAdapter(Date.class, new DateTypeDeserializer());

                if (!response.isSuccessful()){
                    textViewResult.setText("CODE: "+response.code());
                    return;
                }

                for (Producto producto: productos) {

                    String content = "";
                    //content += producto.toString();
                    content += "ID: " + producto.getCodigo() + "\n";
                    content += "NOMBRE: " +producto.getNombre() + "\n";
                    content += "PRECIO: " + producto.getPrecio() + "\n";
                    content += "DESCRIPCION: " + producto.getDescripcion() + "\n";
                    content += "FECHA_ALTA: " + producto.getFechaAlta() + "\n";
                    content += "DESCATALOGADO: " + producto.isDescatalogado() + "\n";
                    content += "CATEGORIA: " + producto.getCategoria() + "\n";
                    textViewResult.append(content);
                 }

            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                textViewResult.setText("SI NADA HA IDO BIEN" + t.getMessage());

            }
        });

    }


}
