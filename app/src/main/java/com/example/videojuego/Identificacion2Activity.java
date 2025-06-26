package com.example.videojuego;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Identificacion2Activity extends AppCompatActivity {
    TextView nombreEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.identificacion2);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        );

        nombreEntry = findViewById(R.id.nombreEntry);

        String nombre = getIntent().getStringExtra("nombreJugador");

        if (nombre != null && !nombre.isEmpty()) {
            nombreEntry.setText(nombre);
        } else {
            nombreEntry.setText("Jugador desconocido");
        }
    }
}
