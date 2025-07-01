package com.example.videojuego;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class Identificacion2Activity extends AppCompatActivity {
    TextView nombreEntry;
    private static final int DURACION_MOSTRAR = 3000; // 3 segundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.identificacion2);

        // Esperar 3 segundos y volver al menú principal
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(Identificacion2Activity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // limpia pila de actividades
            startActivity(intent);
            finish(); // cierra esta actividad
        }, DURACION_MOSTRAR);

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
        if (nombre == null || nombre.isEmpty()) {
            SharedPreferences prefs = getSharedPreferences("MiVideojuegoPrefs", MODE_PRIVATE);
            nombre = prefs.getString("nombreJugador", "");
        }

        if (!nombre.isEmpty()) {
            nombreEntry.setText("" + nombre);
        } else {
            nombreEntry.setText("Jugador desconocido");
        }

    }
}
