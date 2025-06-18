package com.example.videojuego;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SiguienteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adios); // crea un layout simple con un botón "Comenzar"
    }
}