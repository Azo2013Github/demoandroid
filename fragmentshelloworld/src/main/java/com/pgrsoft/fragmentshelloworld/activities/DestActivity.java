package com.pgrsoft.fragmentshelloworld.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.pgrsoft.fragmentshelloworld.R;
import com.pgrsoft.fragmentshelloworld.interfaceFragment.ComunicaMenu;

public class DestActivity extends AppCompatActivity implements ComunicaMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dest);
    }


    @Override
    public void menu(int botonPulsado) {

    }
}
