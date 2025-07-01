package com.example.videojuego;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class CreditosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creditos);

        boolean desdePausa = getIntent().getBooleanExtra("desdePausa", false);

        ConstraintLayout rootLayout = findViewById(R.id.cree);
        rootLayout.setOnClickListener(v -> {
            Intent intent;
            if (desdePausa) {
                // Si viene desde Pausa → ve a la nota (DemoActivity)
                intent = new Intent(CreditosActivity.this, DemoActivity.class);
            } else {
                // Si viene desde Menú → regresa al menú (MainActivity)
                intent = new Intent(CreditosActivity.this, MainActivity.class);
            }
            startActivity(intent);
            finish(); // Cierra Créditos
        });

        // Pantalla completa
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        );
    }
}
