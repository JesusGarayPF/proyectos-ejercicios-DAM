package dm2e.jesus.parejascartas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "juegodeparejas.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_JUGADORES = "jugadores";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOMBRE = "nombre";
    private static final String COLUMN_PUNTUACION = "puntuacion";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_JUGADORES + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NOMBRE + " TEXT NOT NULL, "
                + COLUMN_PUNTUACION + " INTEGER NOT NULL)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JUGADORES);
        onCreate(db);
    }

    public boolean insertarJugador(String nombre, int puntuacion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, nombre);
        values.put(COLUMN_PUNTUACION, puntuacion);
        long result = db.insert(TABLE_JUGADORES, null, values);
        db.close();
        return result != -1;
    }

    public boolean existeJugador(String nombre) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_JUGADORES, null, COLUMN_NOMBRE + " = ?", new String[]{nombre}, null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

    public int obtenerPuntuacion(String nombre) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_JUGADORES, new String[]{COLUMN_PUNTUACION}, COLUMN_NOMBRE + " = ?", new String[]{nombre}, null, null, null);
        int puntuacion = -1;
        if (cursor.moveToFirst()) {
            puntuacion = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return puntuacion;
    }

    public boolean actualizarPuntuacion(String nombre, int nuevaPuntuacion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PUNTUACION, nuevaPuntuacion);
        int rowsAffected = db.update(TABLE_JUGADORES, values, COLUMN_NOMBRE + " = ?", new String[]{nombre});
        db.close();
        return rowsAffected > 0;
    }
}

