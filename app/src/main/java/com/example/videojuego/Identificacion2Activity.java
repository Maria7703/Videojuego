package com.example.videojuego;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Identificacion2Activity extends AppCompatActivity {
    TextView nombreEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.identificacion2);

        nombreEntry = findViewById(R.id.nombreEntry);

        String nombre = getIntent().getStringExtra("nombreJugador");

        if (nombre != null && !nombre.isEmpty()) {
            nombreEntry.setText(nombre);
        } else {
            nombreEntry.setText("Jugador desconocido");
        }
    }
}
