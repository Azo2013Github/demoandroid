package com.pgrsoft.fragmentshelloworld.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.pgrsoft.fragmentshelloworld.R;
import com.pgrsoft.fragmentshelloworld.interfaceFragment.ComunicaMenu;

public class MainActivity extends AppCompatActivity implements ComunicaMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void menu(int botonPulsado) {

        // Estando donde estamos (mainActivity) lo que toca "si  o si" es
        // largarnos a la activitdad DestActivity
        // Para ello programaos el "aparato" Intent
        Intent intent = new Intent(this, DestActivity.class);

        // A DestActivity le pasamos la informacion sobre el numero de boton que
        // seha pulsado. que sera el 0, el 1, o el 2
        intent.putExtra("BOTON_PULSADO", botonPulsado);

        startActivity(intent);

    }
}
