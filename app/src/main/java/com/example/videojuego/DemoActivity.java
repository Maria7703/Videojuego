package com.example.videojuego;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class DemoActivity extends AppCompatActivity {
    private View[] hojas;
    private int hojaActual = 0;
    private EditText notaEditText;
    private static final String PREFS_NAME = "MiVideojuegoPrefs";
    private static final String CLAVE_NOTA = "nota_guardada";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demmo);  // ✅ Siempre va primero

        // Obtener si es nueva partida
        boolean nuevaPartida = getIntent().getBooleanExtra("nuevaPartida", false);
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        // Referencia al campo de nota
        notaEditText = findViewById(R.id.editTextText);

        if (nuevaPartida) {
            editor.remove(CLAVE_NOTA);
            editor.apply();
            notaEditText.setText("");
        } else {
            String notaGuardada = prefs.getString(CLAVE_NOTA, "");
            notaEditText.setText(notaGuardada);
        }

        // Inicializar las hojas
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
        botonIrAContrasena.setOnClickListener(v -> {
            Intent intent = new Intent(DemoActivity.this, ContrasenaActivity.class);
            startActivity(intent);
        });
    }

    private void cambiarHoja(int nuevaHoja) {
        if (nuevaHoja >= 0 && nuevaHoja < hojas.length) {
            hojas[hojaActual].setVisibility(View.GONE);
            hojaActual = nuevaHoja;
            hojas[hojaActual].setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(CLAVE_NOTA, notaEditText.getText().toString());
        editor.apply();
        boolean nuevaPartida = prefs.getBoolean("nuevaPartida", false);

        // limpiar la bandera después de usarla
        editor.putBoolean("nuevaPartida", false);
        editor.apply();
    }
}
