package com.example.videojuego;

import android.content.Intent;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.view.KeyEvent;


public class PantallaCompuActivity extends AppCompatActivity {

    EditText usuarioInput;
    BaseDeDatosHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_compu);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        );

        usuarioInput = findViewById(R.id.usuarioInput);
        ImageButton botonEnter = findViewById(R.id.botonEnter);
        dbHelper = new BaseDeDatosHelper(this);

        botonEnter.setOnClickListener(v -> {
            String nombre = usuarioInput.getText().toString().trim();
            if (!nombre.isEmpty()) {
                // ‑‑‑ Guarda el nombre para toda la app
                SharedPreferences prefs = getSharedPreferences("MiVideojuegoPrefs", MODE_PRIVATE);
                prefs.edit().putString("nombreJugador", nombre).apply();

                Intent intent = new Intent(this, IdentificacionActivity.class);
                intent.putExtra("nombreJugador", nombre);
                intent.putExtra("nuevaPartida",
                        getIntent().getBooleanExtra("nuevaPartida", false)); // re‑envía la bandera
                startActivity(intent);
                finish();



            } else {
                Toast.makeText(this, "Por favor ingresa un nombre", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void guardarNombre(String nombre) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(BaseDeDatosHelper.COLUMNA_NOMBRE, nombre);
        db.insert(BaseDeDatosHelper.TABLA_USUARIO, null, valores);
        db.close();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            Intent intent = new Intent(PantallaCompuActivity.this, IdentificacionActivity.class);
            startActivity(intent); // ❌ Aquí no se reenvía el nombre ni nuevaPartida
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
