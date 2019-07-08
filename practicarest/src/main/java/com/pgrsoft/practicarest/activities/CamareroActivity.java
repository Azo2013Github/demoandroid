package com.pgrsoft.practicarest.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.pgrsoft.practicarest.R;
import com.pgrsoft.practicarest.interfaz.JsonPlaceHolderApi;
import com.pgrsoft.practicarest.model.Camarero;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CamareroActivity extends AppCompatActivity {

    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camarero);
        textViewResult = (TextView) findViewById(R.id.idResultado);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pedi-gest.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        createCamareros();
        getCamareros();

    }

    private void getCamareros(){
        Call<List<Camarero>> call = jsonPlaceHolderApi.getCamareros();


        call.enqueue(new Callback<List<Camarero>>() {
            @Override
            public void onResponse(Call<List<Camarero>> call, Response<List<Camarero>> response) {

                if (!response.isSuccessful()){
                    textViewResult.setText("Code: "+response.code());
                    return;
                }

                List<Camarero> camareros = response.body();

                for (Camarero camarero: camareros) {

                    String content = "";
                    content += "CODIGO: " + camarero.getCodigo()+ "\n\n ";
                    content += "NOMBRE: " + camarero.getNombre() + "\n\n ";

                    textViewResult.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Camarero>> call, Throwable t) {

                textViewResult.setText(t.getMessage());
            }
        });

    }

    private void createCamareros(){
        final Camarero camarero = new Camarero(189,"Raul Iniesta Garcia");
        Call<Camarero> call = jsonPlaceHolderApi.createCamarero(camarero);

        call.enqueue(new Callback<Camarero>() {

            @Override
            public void onResponse(Call<Camarero> call, Response<Camarero> response) {

                if (!response.isSuccessful()){
                    textViewResult.setText("Code: "+response.code());
                    return;
                }

                Camarero camareroResponse = response.body();

                String content = "";
                content += "CODE: " + response.code() + "\n";
                content += "ID: " + camarero.getCodigo() + "\n";
                content += "NOMBRE: " + camarero.getNombre() + "\n";

                textViewResult.setText(content);
            }

            @Override
            public void onFailure(Call<Camarero> call, Throwable t) {
                textViewResult.setText(t.getMessage());

            }
        });
    }

}