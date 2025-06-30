package com.example.videojuego;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class SiguienteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adios); // crea un layout simple con un botón "Comenzar"

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        );

        ImageButton carpeta1 = findViewById(R.id.carpeta1);
        carpeta1.setOnClickListener(v -> {
            Intent intent = new Intent(SiguienteActivity.this, Foto1Activity.class);
            startActivity(intent);
        });

        ImageButton carpeta2 = findViewById(R.id.carpeta2);
        carpeta2.setOnClickListener(v -> {
            Intent intent = new Intent(SiguienteActivity.this, Foto2Activity.class);
            startActivity(intent);
        });

        ImageButton carpeta3 = findViewById(R.id.carpeta3);
        carpeta3.setOnClickListener(v -> {
            Intent intent = new Intent(SiguienteActivity.this, Foto3Activity.class);
            startActivity(intent);
        });

        ImageButton carpeta4 = findViewById(R.id.carpeta4);
        carpeta4.setOnClickListener(v -> {
            Intent intent = new Intent(SiguienteActivity.this, Foto4Activity.class);
            startActivity(intent);
        });

        ImageButton carpeta5 = findViewById(R.id.carpeta5);
        carpeta5.setOnClickListener(v -> {
            Intent intent = new Intent(SiguienteActivity.this, Foto5Activity.class);
            startActivity(intent);
        });

        ImageButton carpeta6 = findViewById(R.id.carpeta6);
        carpeta6.setOnClickListener(v -> {
            Intent intent = new Intent(SiguienteActivity.this, Foto6Activity.class);
            startActivity(intent);
        });


    }
}