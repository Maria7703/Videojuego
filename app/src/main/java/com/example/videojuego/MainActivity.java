package com.example.videojuego;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageButton btnNueva, btnContinuar, btnAjustes, btnSalir, btnTienda;

    private static final String PREFS_NAME = "MiVideojuegoPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // pantalla inmersiva
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        );

        // enlazar botones
        btnNueva       = findViewById(R.id.btnnueva);
        btnContinuar   = findViewById(R.id.btncontinuar);
        btnAjustes     = findViewById(R.id.bntajustes);
        btnSalir       = findViewById(R.id.btnsalir);
        btnTienda      = findViewById(R.id.tienda);

        /* ------------------- NUEVA PARTIDA ------------------- */
        btnNueva.setOnClickListener(v -> {
            // 1) Borra nota e intentos guardados
            SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            prefs.edit()
                    .remove("nota_guardada")
                    .remove("intentos_guardados")
                    .apply();

            // 2) Lanza flujo de nueva partida
            Intent nueva = new Intent(MainActivity.this, PantallaCompuActivity.class);
            nueva.putExtra("nuevaPartida", true);
            startActivity(nueva);
        });

        /* ------------------- CONTINUAR PARTIDA ------------------- */
        btnContinuar.setOnClickListener(v -> {
            Intent continuar = new Intent(MainActivity.this, DemoActivity.class);
            continuar.putExtra("nuevaPartida", false);
            startActivity(continuar);
        });

        /* ------------------- OTROS BOTONES ------------------- */
        btnAjustes.setOnClickListener(v ->
                Toast.makeText(this, "Abrir Ajustes", Toast.LENGTH_SHORT).show());

        btnTienda.setOnClickListener(v ->
                Toast.makeText(this, "Abrir Tienda", Toast.LENGTH_SHORT).show());

        btnSalir.setOnClickListener(v -> {
            Toast.makeText(this, "Saliendo del juego...", Toast.LENGTH_SHORT).show();
            finishAffinity(); // Cierra todas las Activities
            System.exit(0);   // Finaliza el proceso de la app
        });
    }
}
