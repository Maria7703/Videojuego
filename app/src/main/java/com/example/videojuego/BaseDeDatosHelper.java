package com.example.videojuego;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDeDatosHelper extends SQLiteOpenHelper {

    private static final String NOMBRE_BD = "jugador.db";
    private static final int VERSION_BD = 1;
    public static final String TABLA_USUARIO = "usuarios";
    public static final String COLUMNA_ID = "id";
    public static final String COLUMNA_NOMBRE = "nombre";

    public BaseDeDatosHelper(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String crearTabla = "CREATE TABLE " + TABLA_USUARIO + " (" +
                COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMNA_NOMBRE + " TEXT)";
        db.execSQL(crearTabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_USUARIO);
        onCreate(db);
    }
}

