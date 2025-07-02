package com.example.videojuego;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class PistaActivity extends AppCompatActivity {
    private View[] pistas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pista);

        // Pantalla completa inmersiva
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        );

        pistas = new View[]{
                findViewById(R.id.pista1),
                findViewById(R.id.pista2),
                findViewById(R.id.pista3),
                findViewById(R.id.pista4),
                findViewById(R.id.pista5)
        };

        // Ocultar todas las pistas
        for (View pista : pistas) {
            pista.setVisibility(View.GONE);
        }

        // Obtener la pista a mostrar (pistas van del 1 al 5, índice va de 0 a 4)
        int numeroPista = getIntent().getIntExtra("pistaActual", 1);
        int index = numeroPista - 1;

        String origen = getIntent().getStringExtra("origen");

        if (index >= 0 && index < pistas.length) {
            pistas[index].setVisibility(View.VISIBLE);
        }

        // Volver a DemoActivity al hacer clic en cualquier parte
        ConstraintLayout rootLayout = findViewById(R.id.rootLayout);
        rootLayout.setOnClickListener(v -> {
            Intent intent;
            if ("DemoActivity".equals(origen)) {
                intent = new Intent(PistaActivity.this, DemoActivity.class);
            } else if ("ContrasenaActivity".equals(origen)) {
                intent = new Intent(PistaActivity.this, ContrasenaActivity.class);
            } else {
                // Valor por defecto si no se reconoce el origen
                intent = new Intent(PistaActivity.this, DemoActivity.class);
            }

            intent.putExtra("pistaActual", numeroPista);
            startActivity(intent);
            finish(); // opcional

        });
    }
}
