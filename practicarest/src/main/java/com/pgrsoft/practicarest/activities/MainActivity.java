package com.pgrsoft.practicarest.activities;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pgrsoft.practicarest.R;
import com.pgrsoft.practicarest.activities.ProductoActivity;
import com.pgrsoft.practicarest.model.Camarero;
import com.pgrsoft.practicarest.model.Pedido;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button botonProducto;
    private Button botonCamarero;
    private Button buttonPedido;
    private Button botonActualizarCamarero;
    private Button botonActualizarProducto;
    private Button botonActualizarPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonProducto = (Button) findViewById(R.id.idButtonProducto);
        botonCamarero = (Button) findViewById(R.id.idButtonCamarero);
        buttonPedido = (Button) findViewById(R.id.idButtonPedido);
        botonActualizarCamarero = (Button) findViewById(R.id.idActualozarCamarero);
        botonActualizarProducto = (Button) findViewById(R.id.idActulizarProducto);
        botonActualizarPedido = (Button) findViewById(R.id.idActualizarPedido);

        buttonPedido.setOnClickListener(this);
        botonCamarero.setOnClickListener(this);
        botonProducto.setOnClickListener(this);
        botonActualizarCamarero.setOnClickListener(this);
        botonActualizarProducto.setOnClickListener(this);
        botonActualizarPedido.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {

        //Toast.makeText(this, "FUNCIONA", Toast.LENGTH_LONG).show();
        int option = 0;
        Intent intent = null;
        switch (view.getId()){
            case R.id.idButtonProducto:

                intent = new Intent(this, ProductoActivity.class);
                break;

            case R.id.idButtonPedido:

                intent = new Intent(this, PedidoActivity.class);
                break;

            case R.id.idButtonCamarero:

                intent = new Intent(this, CamareroActivity.class);
                break;
            case R.id.idActualozarCamarero:

                intent = new Intent(this, AltaCamareroActivity.class);
                break;
            case R.id.idActulizarProducto:

                intent = new Intent(this, ProductoActivity.class);
                break;
            case R.id.idActualizarPedido:

                intent = new Intent(this, PedidoActivity.class);
                break;


        }
        startActivity(intent);
    }
}
