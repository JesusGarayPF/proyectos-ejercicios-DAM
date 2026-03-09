package dm2e.jesus.basedatos;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class InsertarActivity extends AppCompatActivity {

    private EditText etNombre, etDescripcion, etFecha, etEmail, etTelefono;
    private Button btnGuardar;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);

        etNombre = findViewById(R.id.etNombre);
        etDescripcion = findViewById(R.id.etDescripcion);
        etFecha = findViewById(R.id.etFecha);
        etEmail = findViewById(R.id.etEmail);
        etTelefono = findViewById(R.id.etTelefono);
        btnGuardar = findViewById(R.id.btnGuardar);

        db = new DatabaseHelper(this);

        btnGuardar.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString();
            String descripcion = etDescripcion.getText().toString();
            String fecha = etFecha.getText().toString();
            String email = etEmail.getText().toString();
            String telefono = etTelefono.getText().toString();

            boolean resultado = db.insertarDatos(nombre, descripcion, fecha, email, telefono);
            if (resultado) {
                Toast.makeText(InsertarActivity.this, "Datos insertados correctamente", Toast.LENGTH_SHORT).show();
                limpiarCampos();
            } else {
                Toast.makeText(InsertarActivity.this, "Error al insertar los datos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void limpiarCampos() {
        etNombre.setText("");
        etDescripcion.setText("");
        etFecha.setText("");
        etEmail.setText("");
        etTelefono.setText("");
    }
}

