package com.example.videojuego;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ContrasenaActivity extends AppCompatActivity {

    private EditText usuarioInput;
    private ImageButton botonEnter;
    private int intentos = 0;
    private static final String CONTRASENA_CORRECTA = "REALS";
    private BaseDeDatosHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compu_contra);

        usuarioInput = findViewById(R.id.usuarioInput);
        botonEnter = findViewById(R.id.botonEnter);
        dbHelper = new BaseDeDatosHelper(this);

        botonEnter.setOnClickListener(v -> {
            String contrasenaIngresada = usuarioInput.getText().toString().trim();

            if (contrasenaIngresada.equals(CONTRASENA_CORRECTA)) {
                Intent intent = new Intent(ContrasenaActivity.this, SiguienteActivity.class);
                startActivity(intent);
                finish();
            } else {
                intentos++;

                if (intentos >= 3) {
                    // OBTENER NOMBRE DEL JUGADOR DESDE LA BD
                    String nombreJugador = obtenerNombreJugadorDesdeBD();

                    Intent intent = new Intent(ContrasenaActivity.this, Identificacion2Activity.class);
                    intent.putExtra("nombreJugador", nombreJugador);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "Contraseña incorrecta. Intento " + intentos + " de 3", Toast.LENGTH_SHORT).show();
                }
            }
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
