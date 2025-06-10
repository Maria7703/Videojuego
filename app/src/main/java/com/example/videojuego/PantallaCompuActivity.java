package com.example.videojuego;

import android.content.Intent;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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

        usuarioInput = findViewById(R.id.usuarioInput);
        ImageButton botonEnter = findViewById(R.id.botonEnter);
        dbHelper = new BaseDeDatosHelper(this);

        botonEnter.setOnClickListener(v -> {
            String nombre = usuarioInput.getText().toString().trim();
            if (!nombre.isEmpty()) {
                guardarNombre(nombre);

                // Ir a la pantalla de identificación
                Intent intent = new Intent(PantallaCompuActivity.this, IdentificacionActivity.class);
                intent.putExtra("nombreJugador", nombre);
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
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
