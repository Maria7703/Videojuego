package com.example.videojuego;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;


import androidx.appcompat.app.AppCompatActivity;

public class DemoActivity extends AppCompatActivity {

    private View[] hojas;
    private int hojaActual = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demmo);

        hojas = new View[]{
                findViewById(R.id.hoja1),
                findViewById(R.id.hoja2),
                findViewById(R.id.hoja3),
                findViewById(R.id.hoja4),
                findViewById(R.id.hoja5)
        };

        ImageButton btnAnterior = findViewById(R.id.botonAnterior);
        ImageButton btnSiguiente = findViewById(R.id.botonSiguiente);

        btnAnterior.setOnClickListener(v -> cambiarHoja(hojaActual - 1));
        btnSiguiente.setOnClickListener(v -> cambiarHoja(hojaActual + 1));

        ImageButton botonIrAContrasena = findViewById(R.id.imageButton);
        botonIrAContrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DemoActivity.this, ContrasenaActivity.class);
                startActivity(intent);
            }
        });

    }

    private void cambiarHoja(int nuevaHoja) {
        if (nuevaHoja >= 0 && nuevaHoja < hojas.length) {
            hojas[hojaActual].setVisibility(View.GONE);
            hojaActual = nuevaHoja;
            hojas[hojaActual].setVisibility(View.VISIBLE);
        }
    }
}
