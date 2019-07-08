package com.pgrsoft.practicarest.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.pgrsoft.practicarest.R;
import com.pgrsoft.practicarest.interfaz.JsonPlaceHolderApi;
import com.pgrsoft.practicarest.model.Camarero;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AltaCamareroActivity extends AppCompatActivity {

    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_camarero);

        textViewResult = (TextView) findViewById(R.id.idResultado);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pedi-gest.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        createCamareros();



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

                textViewResult.append(content);
            }

            @Override
            public void onFailure(Call<Camarero> call, Throwable t) {
                textViewResult.setText(t.getMessage());

            }
        });
    }
}
