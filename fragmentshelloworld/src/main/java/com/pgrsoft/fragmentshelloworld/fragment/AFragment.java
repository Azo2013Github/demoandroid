package com.pgrsoft.fragmentshelloworld.fragment;


import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pgrsoft.fragmentshelloworld.R;
import com.pgrsoft.fragmentshelloworld.interfaceFragment.ComunicaMenu;

/**
 * A simple {@link Fragment} subclass.
 */
public class AFragment extends Fragment implements ComunicaMenu {



    public AFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_a, container, false);
    }

    @Override
    public void menu(int botonPulsado) {


    }
}
