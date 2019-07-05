package com.pgrsoft.practicarest.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.pgrsoft.practicarest.R;
import com.pgrsoft.practicarest.interfaz.JsonPlaceHolderApi;
import com.pgrsoft.practicarest.model.Camarero;
import com.pgrsoft.practicarest.model.LineaPedido;
import com.pgrsoft.practicarest.model.Pedido;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PedidoActivity extends AppCompatActivity {

    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private TextView textViewResult;
    private List<LineaPedido> lineaPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        textViewResult = (TextView) findViewById(R.id.idResultado);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pedi-gest.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
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

                List<Pedido> pedidos = response.body();
                for (Pedido pedido: pedidos) {

                    String content = "";
                    content = "ID: " + pedido.getId() + "\n";
                    content = "FECHA: " + pedido.getDate() + "\n";
                    content = "MESA: " + pedido.getMesa() + "\n";
                    content = "CAMARERO ID: " + pedido.getCamarero().getCodigo() + "\n";
                    content = "CAMARERO NOMBRE: " + pedido.getCamarero().getNombre() + "\n";

                    textViewResult.setText(content);

                }


            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {
                textViewResult.setText("MESSAGE: " + t.getMessage());

            }
        });


    }

    /*@Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {

                if (!response.isSuccessful()){
                    textViewResult.setText("Code: "+response.code());
                    return;
                }

                List<Pedido> pedidos = response.body();

                for (Pedido pedido: pedidos) {

                    String content = "";
                    content += "CODIGO: " + pedido.getId() + "\n\n ";
                    content += "NOMBRE: " + camarero.getNombre() + "\n\n ";

                    textViewResult.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Camarero>> call, Throwable t) {

                textViewResult.setText(t.getMessage());
            }
        });
*/
}
