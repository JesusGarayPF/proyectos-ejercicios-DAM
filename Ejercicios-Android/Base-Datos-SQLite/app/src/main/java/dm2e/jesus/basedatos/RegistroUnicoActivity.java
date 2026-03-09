package dm2e.jesus.basedatos;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegistroUnicoActivity extends AppCompatActivity {

    private EditText etIdUnico;
    private Button btnBuscarRegistro;
    private TextView tvRegistroUnico;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_unico);

        etIdUnico = findViewById(R.id.etIdUnico);
        btnBuscarRegistro = findViewById(R.id.btnBuscarRegistro);
        tvRegistroUnico = findViewById(R.id.tvRegistroUnico);

        db = new DatabaseHelper(this);

        btnBuscarRegistro.setOnClickListener(v -> {
            String id = etIdUnico.getText().toString();
            Cursor cursor = db.obtenerRegistro(id);

            if (cursor.getCount() == 0) {
                Toast.makeText(this, "Registro no encontrado", Toast.LENGTH_SHORT).show();
                tvRegistroUnico.setText("");
                return;
            }

            cursor.moveToFirst();
            String registro = "ID: " + cursor.getString(0) +
                    "\nNombre: " + cursor.getString(1) +
                    "\nDescripción: " + cursor.getString(2) +
                    "\nFecha: " + cursor.getString(3) +
                    "\nEmail: " + cursor.getString(4) +
                    "\nTeléfono: " + cursor.getString(5);
            tvRegistroUnico.setText(registro);
        });
    }
}

