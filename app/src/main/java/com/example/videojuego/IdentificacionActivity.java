package com.example.videojuego;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;


public class IdentificacionActivity extends AppCompatActivity {

    TextView nombreEntry; // TextView donde mostrarás el nombre

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.identificion);

        nombreEntry = findViewById(R.id.nombreEntry); // Asegúrate que es TextView, no EditText

        // Obtener el nombre del intent
        String nombre = getIntent().getStringExtra("nombreJugador");
        nombreEntry.setText(nombre);

        // Mostrar el nombre en el TextView
        if (nombre != null && !nombre.isEmpty()) {
            nombreEntry.setText("" + nombre);
        } else {
            nombreEntry.setText("Jugador desconocido");
        }

        // Esperar 3 segundos y pasar a la pantalla del juego
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(IdentificacionActivity.this, DemoActivity.class);
            intent.putExtra("nombreJugador", nombre); // Si quieres usarlo en la siguiente también
            startActivity(intent);
            finish();
        }, 3000); // 3000 milisegundos = 3 segundos

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(IdentificacionActivity.this, DemoActivity.class);
                startActivity(intent);
                finish(); // Para cerrar esta pantalla
            }
        }, 3000); // 3000 ms = 3 segundos

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(IdentificacionActivity.this, DemoActivity.class);
            boolean nuevaPartida = getIntent().getBooleanExtra("nuevaPartida", false);
            intent.putExtra("nuevaPartida", nuevaPartida);
            startActivity(intent);
            finish();
        }, 3000); // o los segundos que uses


    }

}

