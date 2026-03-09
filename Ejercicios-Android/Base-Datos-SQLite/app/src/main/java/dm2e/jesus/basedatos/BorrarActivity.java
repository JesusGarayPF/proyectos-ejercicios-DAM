package dm2e.jesus.basedatos;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class BorrarActivity extends AppCompatActivity {

    private EditText etIdBorrar;
    private Button btnBorrarRegistro;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar);

        etIdBorrar = findViewById(R.id.etIdBorrar);
        btnBorrarRegistro = findViewById(R.id.btnBorrarRegistro);

        db = new DatabaseHelper(this);

        btnBorrarRegistro.setOnClickListener(v -> {
            String id = etIdBorrar.getText().toString();
            int resultado = db.borrarDatos(id);
            if (resultado > 0) {
                Toast.makeText(BorrarActivity.this, "Registro borrado correctamente", Toast.LENGTH_SHORT).show();
                etIdBorrar.setText("");
            } else {
                Toast.makeText(BorrarActivity.this, "Error al borrar el registro", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

