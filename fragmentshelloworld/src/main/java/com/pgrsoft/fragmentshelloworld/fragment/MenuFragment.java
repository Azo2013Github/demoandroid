package com.pgrsoft.fragmentshelloworld.fragment;


import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.pgrsoft.fragmentshelloworld.R;
import com.pgrsoft.fragmentshelloworld.interfaceFragment.ComunicaMenu;


public class MenuFragment extends Fragment {


    // Creamos las referencias a los botones:
    private  final int[] BOTONES_MENU = {R.id.boton1,
                                         R.id.boton2,
                                         R.id.boton3};

    public MenuFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // se genera la view a partir del XML
        View miMenu = inflater.inflate(R.layout.fragment_menu, container, false);

        // Declaracion una variable de tipo ImageButton
        // Vamos a iterar todos y cada uno de los botones y en cada vuelta del for
        // añadimos un listener añ boton de turno.
        // (el boton de turno es botonMenu)
        ImageButton botonMenu;

        // para cada boton ....
        for (int i = 0; i < BOTONES_MENU.length; i++){

            botonMenu = (ImageButton) miMenu.findViewById(BOTONES_MENU[i]);

            final int BOTON_i = i; // Ha de ser una constante porque la vamos a
                                // utilizar en un listener...

            // Añadimos un listener a cada uno de los botones
            botonMenu.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    // Hemos de detectar en que actividad nos encontramos...
                    Activity actividadActual = getActivity();

                    //Hay que enviar la información al interface ComunicaMenu
                    Log.d("****","pulsamos y enviamos info del boton: " + BOTON_i);

                    // Hemos de invocar al método .menu(boton) de la actividad actual.
                    // Pero para ello, la actividad la hemos de tratar como ComunicaMenu
                    // Por eso hacemos el casting...

                    ComunicaMenu cm = (ComunicaMenu) actividadActual;
                    cm.menu(BOTON_i);

                }
            });

        }

        return miMenu;
    }

}
