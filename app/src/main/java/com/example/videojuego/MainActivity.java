package com.example.videojuego;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declarar todos los botones
    ImageButton btnNueva, btnContinuar, btnCapitulos, btnAjustes, btnSalir, btnTienda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enlazar con los IDs del XML (asegúrate de que coincidan)
        btnNueva = findViewById(R.id.btnnueva); // <-- Este ID debe existir en activity_main.xml
        btnContinuar = findViewById(R.id.btncontinuar);
        btnCapitulos = findViewById(R.id.btncapitulos);
        btnAjustes = findViewById(R.id.bntajustes);
        btnSalir = findViewById(R.id.btnsalir);
        btnTienda = findViewById(R.id.tienda);

        // Acciones de los botones
        btnNueva.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PantallaCompuActivity.class);
            startActivity(intent);
        });

        btnContinuar.setOnClickListener(v -> Toast.makeText(this, "Continuar Partida", Toast.LENGTH_SHORT).show());
        btnCapitulos.setOnClickListener(v -> Toast.makeText(this, "Ver Capítulos", Toast.LENGTH_SHORT).show());
        btnAjustes.setOnClickListener(v -> Toast.makeText(this, "Abrir Ajustes", Toast.LENGTH_SHORT).show());
        btnSalir.setOnClickListener(v -> {
            Toast.makeText(this, "Saliendo del juego...", Toast.LENGTH_SHORT).show();
            finish();
        });
        btnTienda.setOnClickListener(v -> Toast.makeText(this, "Abrir Tienda", Toast.LENGTH_SHORT).show());

        ImageButton botonContinuar = findViewById(R.id.btncontinuar); // o como lo tengas
        botonContinuar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DemoActivity.class); // o el nombre que uses
            startActivity(intent);
        });

        btnNueva.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PantallaCompuActivity.class);
            intent.putExtra("nuevaPartida", true); // <- importante
            startActivity(intent);
        });

        btnContinuar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DemoActivity.class);
            intent.putExtra("nuevaPartida", false); // <- no borrar
            startActivity(intent);
        });

    }
}
