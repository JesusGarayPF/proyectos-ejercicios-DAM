package dm2e.jesus.basedatos;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ModificarActivity extends AppCompatActivity {

    private EditText etIdModificar, etNombreModificar, etDescripcionModificar, etFechaModificar, etEmailModificar, etTelefonoModificar,
            etIdActual, etNombreActual, etDescripcionActual, etFechaActual, etEmailActual, etTelefonoActual;
    private Button btnActualizar, btnBuscar;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        etIdActual = findViewById(R.id.etIdActual);
        etNombreActual = findViewById(R.id.etNombreActual);
        etDescripcionActual = findViewById(R.id.etDescripcionActual);
        etFechaActual = findViewById(R.id.etFechaActual);
        etEmailActual = findViewById(R.id.etEmailActual);
        etTelefonoActual = findViewById(R.id.etTelefonoActual);

        etIdModificar = findViewById(R.id.etIdModificar);
        etNombreModificar = findViewById(R.id.etNombreModificar);
        etDescripcionModificar = findViewById(R.id.etDescripcionModificar);
        etFechaModificar = findViewById(R.id.etFechaModificar);
        etEmailModificar = findViewById(R.id.etEmailModificar);
        etTelefonoModificar = findViewById(R.id.etTelefonoModificar);

        btnActualizar = findViewById(R.id.btnActualizar);
        btnBuscar = findViewById(R.id.btnBuscar);

        db = new DatabaseHelper(this);

        btnBuscar.setOnClickListener(v -> {
            String id = etIdModificar.getText().toString();
            if (id.isEmpty()) {
                Toast.makeText(this, "Por favor, ingresa un ID para buscar", Toast.LENGTH_SHORT).show();
                return;
            }
            buscarRegistro(id);
        });

        btnActualizar.setOnClickListener(v -> {
            String id = etIdModificar.getText().toString();
            String nombre = etNombreModificar.getText().toString();
            String descripcion = etDescripcionModificar.getText().toString();
            String fecha = etFechaModificar.getText().toString();
            String email = etEmailModificar.getText().toString();
            String telefono = etTelefonoModificar.getText().toString();

            boolean resultado = db.modificarDatos(id, nombre, descripcion, fecha, email, telefono);
            if (resultado) {
                Toast.makeText(ModificarActivity.this, "Datos actualizados correctamente", Toast.LENGTH_SHORT).show();
                limpiarCampos();
            } else {
                Toast.makeText(ModificarActivity.this, "Error al actualizar los datos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void buscarRegistro(String id) {
        Cursor cursor = db.obtenerRegistro(id);
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Registro no encontrado", Toast.LENGTH_SHORT).show();
            limpiarCamposActuales();
            return;
        }

        cursor.moveToFirst();
        etIdActual.setText(cursor.getString(0));
        etNombreActual.setText(cursor.getString(1));
        etDescripcionActual.setText(cursor.getString(2));
        etFechaActual.setText(cursor.getString(3));
        etEmailActual.setText(cursor.getString(4));
        etTelefonoActual.setText(cursor.getString(5));
    }

    private void limpiarCampos() {
        etIdModificar.setText("");
        etNombreModificar.setText("");
        etDescripcionModificar.setText("");
        etFechaModificar.setText("");
        etEmailModificar.setText("");
        etTelefonoModificar.setText("");
    }

    private void limpiarCamposActuales() {
        etIdActual.setText("");
        etNombreActual.setText("");
        etDescripcionActual.setText("");
        etFechaActual.setText("");
        etEmailActual.setText("");
        etTelefonoActual.setText("");
    }
}
