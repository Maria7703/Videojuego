package com.example.videojuego;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DemoActivity extends AppCompatActivity {

    private View[] hojas, actual;
    private int hojaActual = 0, pistaActual = 0;
    private final int TOTAL_PISTAS = 5;


    private EditText notaEditText;
    private static final String PREFS_NAME = "MiVideojuegoPrefs";
    private static final String CLAVE_NOTA = "nota_guardada";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demmo);         // 1) layout primero

        // ——— Pantalla completa inmersiva ———
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        );

        // ——— SharedPreferences (solo una instancia) ———
        SharedPreferences prefs  = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        // ——— Mostrar nombre del jugador ———
        String nombreJugador = prefs.getString("nombreJugador", "Jugador");
        TextView textoNombre = findViewById(R.id.textoNombre);
        textoNombre.setText("Agente: " + nombreJugador);

        // ——— Nota (nueva partida vs continuar) ———
        boolean nuevaPartida = getIntent().getBooleanExtra("nuevaPartida", false);
        notaEditText = findViewById(R.id.editTextText);

        if (nuevaPartida) {
            editor.remove(CLAVE_NOTA).apply();      // limpia nota
            notaEditText.setText("");
        } else {
            notaEditText.setText(prefs.getString(CLAVE_NOTA, ""));
        }

        // ——— Inicializar las “hojas” ———
        hojas = new View[]{
                findViewById(R.id.hoja1),
                findViewById(R.id.hoja2),
                findViewById(R.id.hoja3),
                findViewById(R.id.hoja4),
                findViewById(R.id.hoja5)
        };

        ImageButton btnAnterior   = findViewById(R.id.botonAnterior);
        ImageButton btnSiguiente  = findViewById(R.id.botonSiguiente);
        btnAnterior.setOnClickListener(v -> cambiarHoja(hojaActual - 1));
        btnSiguiente.setOnClickListener(v -> cambiarHoja(hojaActual + 1));

        // ——— Botón para ir a la pantalla de contraseña ———
        findViewById(R.id.imageButton).setOnClickListener(v ->
                startActivity(new Intent(this, ContrasenaActivity.class))
        );

        ImageButton botonPausa = findViewById(R.id.botonPausa);
        botonPausa.setOnClickListener(v -> {
            Intent intent = new Intent(DemoActivity.this, PausaActivity.class);
            intent.putExtra("origen", "DemoActivity");
            startActivity(intent);
        });
        pistaActual = getIntent().getIntExtra("pistaActual", 0);


        ImageButton botonPista = findViewById(R.id.botonPista);
        botonPista.setOnClickListener(v -> {
            if (pistaActual < TOTAL_PISTAS) {
                pistaActual++;  // incrementa
                Intent intent = new Intent(DemoActivity.this, PistaActivity.class);
                intent.putExtra("pistaActual", pistaActual);
                intent.putExtra("origen", "DemoActivity");
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

    @Override
    protected void onPause() {
        super.onPause();

        // Guarda la nota y limpia la bandera nuevaPartida
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(CLAVE_NOTA, notaEditText.getText().toString());
        editor.putBoolean("nuevaPartida", false);
        editor.apply();
    }
}
