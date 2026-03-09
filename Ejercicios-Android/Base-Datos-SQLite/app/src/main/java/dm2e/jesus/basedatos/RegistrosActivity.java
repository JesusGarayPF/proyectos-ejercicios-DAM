package dm2e.jesus.basedatos;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrosActivity extends AppCompatActivity {

    private TextView tvRegistros;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros);

        tvRegistros = findViewById(R.id.tvRegistros);
        db = new DatabaseHelper(this);

        cargarRegistros();
    }

    private void cargarRegistros() {
        Cursor cursor = db.obtenerDatos();
        StringBuilder registros = new StringBuilder();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No hay registros", Toast.LENGTH_SHORT).show();
            return;
        }

        while (cursor.moveToNext()) {
            registros.append("ID: ").append(cursor.getString(0)).append("\n")
                    .append("Nombre: ").append(cursor.getString(1)).append("\n")
                    .append("Descripción: ").append(cursor.getString(2)).append("\n")
                    .append("Fecha: ").append(cursor.getString(3)).append("\n")
                    .append("Email: ").append(cursor.getString(4)).append("\n")
                    .append("Teléfono: ").append(cursor.getString(5)).append("\n\n");
        }

        tvRegistros.setText(registros.toString());
    }
}

