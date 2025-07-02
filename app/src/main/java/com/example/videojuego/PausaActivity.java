package com.example.videojuego;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;


import androidx.appcompat.app.AppCompatActivity;

public class PausaActivity extends AppCompatActivity{

    private static final String PREFS_NAME = "MiVideojuegoPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pausa);  // ✅ Siempre va primero

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


        ImageButton btnReanudar = findViewById(R.id.btnReanudar);
        btnReanudar.setOnClickListener(v -> {
            Intent intent;
            if ("DemoActivity".equals(origen)) {
                intent = new Intent(PausaActivity.this, DemoActivity.class);
            } else if ("ContrasenaActivity".equals(origen)) {
                intent = new Intent(PausaActivity.this, ContrasenaActivity.class);
            } else {
                // Valor por defecto si no se reconoce el origen
                intent = new Intent(PausaActivity.this, DemoActivity.class);
            }            startActivity(intent);
        });

        ImageButton btnMenu = findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(v -> {
            Intent intent = new Intent(PausaActivity.this, MainActivity.class);
            startActivity(intent);
        });

        ImageButton btnCreditos = findViewById(R.id.btnCreditos);
        btnCreditos.setOnClickListener(v -> {
            Intent intent = new Intent(PausaActivity.this, CreditosActivity.class);
            intent.putExtra("desdePausa", true);  // importante
            startActivity(intent);
        });


        ImageButton btnReiniciar = findViewById(R.id.btnReiniciar);
        btnReiniciar.setOnClickListener(v -> {
            try (InputStream is = getAssets().open("estado_default.json")) {
                byte[] buffer = new byte[is.available()];
                is.read(buffer);
                JSONObject obj = new JSONObject(new String(buffer, "UTF-8"));

                SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                prefs.edit()
                        .putString("nota_guardada", obj.getString("nota_guardada"))
                        .putInt("intentos_guardados", obj.getInt("intentos_guardados"))
                        .apply();

                Toast.makeText(this, "Progreso restaurado", Toast.LENGTH_SHORT).show();

                // Redirige a DemoActivity como si fuera nueva partida
                Intent intent = new Intent(PausaActivity.this, DemoActivity.class);
                intent.putExtra("nuevaPartida", true);
                startActivity(intent);
                finish();

            } catch (IOException | JSONException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error al restaurar datos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void limpiarNotaEnPantalla() {
        EditText nota = findViewById(R.id.editTextText);
        if (nota != null) nota.setText("");
    }
}
