package com.pgrsoft.fragmentshelloworld.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;


import android.content.Intent;
import android.os.Bundle;

import com.pgrsoft.fragmentshelloworld.R;
import com.pgrsoft.fragmentshelloworld.interfaceFragment.ComunicaMenu;
import com.pgrsoft.fragmentshelloworld.fragment.AFragment;
import com.pgrsoft.fragmentshelloworld.fragment.BFragment;
import com.pgrsoft.fragmentshelloworld.fragment.CFragment;


public class DestActivity extends AppCompatActivity implements ComunicaMenu {

    private Fragment[] fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dest);

        fragments = new Fragment [3]; //Creamos array de 3 elementos..

        // asignamos un Fragment a cada uno de los elementos del array....
        fragments[0] = new AFragment();
        fragments[1] = new BFragment();
        fragments[2] = new CFragment();

        // Aqui llega la informacion de boton_pulsado : 0, 1, 2.
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            menu(bundle.getInt("BOTON_PULSADO"));
        }



    }


    @Override
    public void menu(int botonPulsado) {

        FragmentManager fragmentManager = getFragmentManager(); //
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // nos pide
        // 1. identificador del contenedor...
        // 2. el fragmento que queremos cargar... hay tres posibilidades

        fragmentTransaction.replace(R.id.destino, fragments[botonPulsado]);
        fragmentTransaction.commit();

    }
}
