package dm2e.jesus.basedatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MiBaseDeDatos.db";
    private static final String TABLE_NAME = "mi_tabla";

    private static final String COL_1 = "ID";
    private static final String COL_2 = "NOMBRE";
    private static final String COL_3 = "DESCRIPCION";
    private static final String COL_4 = "FECHA";
    private static final String COL_5 = "EMAIL";
    private static final String COL_6 = "TELEFONO";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE TEXT, DESCRIPCION TEXT, FECHA DATE, EMAIL TEXT, TELEFONO TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertarDatos(String nombre, String descripcion, String fecha, String email, String telefono) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, nombre);
        contentValues.put(COL_3, descripcion);
        contentValues.put(COL_4, fecha);
        contentValues.put(COL_5, email);
        contentValues.put(COL_6, telefono);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public Cursor obtenerDatos() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public Cursor obtenerRegistro(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE ID = ?", new String[]{id});
    }

    public boolean modificarDatos(String id, String nombre, String descripcion, String fecha, String email, String telefono) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, nombre);
        contentValues.put(COL_3, descripcion);
        contentValues.put(COL_4, fecha);
        contentValues.put(COL_5, email);
        contentValues.put(COL_6, telefono);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id});
        return true;
    }

    public Integer borrarDatos(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{id});
    }
}

