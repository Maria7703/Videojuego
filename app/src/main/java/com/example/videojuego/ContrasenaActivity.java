package com.example.videojuego;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ContrasenaActivity extends AppCompatActivity {

    private EditText usuarioInput;
    private ImageButton botonEnter;
    private int intentos = 0, pistaActual = 0;
    private final int TOTAL_PISTAS = 5;
    private static final String PREFS_NAME = "MiVideojuegoPrefs";
    private static final String CLAVE_INTENTOS = "intentos_guardados";
    private static final String CONTRASENA_CORRECTA = "REALS";

    private BaseDeDatosHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compu_contra);

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
        botonEnter = findViewById(R.id.botonEnter);
        dbHelper = new BaseDeDatosHelper(this);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean nuevaPartida = getIntent().getBooleanExtra("nuevaPartida", false);

        if (nuevaPartida) {
            intentos = 0;
            prefs.edit().remove(CLAVE_INTENTOS).apply();
        } else {
            intentos = prefs.getInt(CLAVE_INTENTOS, 0);
        }

        botonEnter.setOnClickListener(v -> {
            String contrasenaIngresada = usuarioInput.getText().toString().trim();

            if (contrasenaIngresada.equals(CONTRASENA_CORRECTA)) {
                prefs.edit().remove(CLAVE_INTENTOS).apply();
                startActivity(new Intent(this, SiguienteActivity.class));
                finish();
            } else {
                intentos++;
                if (!nuevaPartida) {
                    prefs.edit().putInt(CLAVE_INTENTOS, intentos).apply();
                }

                if (intentos >= 3) {
                    String nombreJugador = prefs.getString("nombreJugador", "");
                    if (nombreJugador.isEmpty()) {
                        nombreJugador = obtenerNombreJugadorDesdeBD();
                    }

                    Intent intentPerdiste = new Intent(this, Identificacion2Activity.class);
                    intentPerdiste.putExtra("nombreJugador", nombreJugador);
                    startActivity(intentPerdiste);
                    finish();
                } else {
                    Toast.makeText(this, "Contraseña incorrecta. Intento " + intentos + " de 3", Toast.LENGTH_SHORT).show();
                }
            }
        });


        ImageButton botonPausa = findViewById(R.id.botonPausa);
        botonPausa.setOnClickListener(v -> {
            Intent intent = new Intent(ContrasenaActivity.this, PausaActivity.class);
            intent.putExtra("origen", "ContrasenaActivity");
            startActivity(intent);
        });

        ImageButton botonPista = findViewById(R.id.botonPista);
        botonPista.setOnClickListener(v -> {
            if (pistaActual < TOTAL_PISTAS) {
                pistaActual++;  // incrementa
                Intent intent = new Intent(ContrasenaActivity.this, PistaActivity.class);
                intent.putExtra("pistaActual", pistaActual);
                intent.putExtra("origen", "ContrasenaActivity");
                startActivity(intent);
            }
        });

        findViewById(R.id.botonRegresar).setOnClickListener(v -> {
            Intent regresarIntent = new Intent(ContrasenaActivity.this, DemoActivity.class);
            startActivity(regresarIntent);
        });
    }

    private String obtenerNombreJugadorDesdeBD() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String nombre = "Jugador desconocido";
        Cursor cursor = db.rawQuery("SELECT nombre FROM usuarios ORDER BY id DESC LIMIT 1", null);
        if (cursor.moveToFirst()) {
            nombre = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return nombre;
    }
}
