package com.example.videojuego;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class TiendaActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tienda);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        );

        String origen = getIntent().getStringExtra("origen");

        ImageButton regresar = findViewById(R.id.regresar);
        regresar.setOnClickListener(v -> {
            Intent intent;
            if ("MainActivity".equals(origen)) {
                intent = new Intent(TiendaActivity.this, MainActivity.class);
            } else if ("PausaActivity".equals(origen)) {
                intent = new Intent(TiendaActivity.this, PausaActivity.class);
            } else {
                // Valor por defecto si no se reconoce el origen
                intent = new Intent(TiendaActivity.this, MainActivity.class);
            }
            startActivity(intent);
        });
    }

}
