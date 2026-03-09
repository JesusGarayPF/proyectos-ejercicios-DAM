package dm2e.jesus.basedatos;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnInsertar, btnModificar, btnBorrar, btnListar, btnRegistroUnico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsertar = findViewById(R.id.btnInsertar);
        btnModificar = findViewById(R.id.btnModificar);
        btnBorrar = findViewById(R.id.btnBorrar);
        btnListar = findViewById(R.id.btnListar);
        btnRegistroUnico = findViewById(R.id.btnRegistroUnico);

        btnInsertar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, InsertarActivity.class);
            startActivity(intent);
        });

        btnModificar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ModificarActivity.class);
            startActivity(intent);
        });

        btnBorrar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BorrarActivity.class);
            startActivity(intent);
        });

        btnListar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegistrosActivity.class);
            startActivity(intent);
        });

        btnRegistroUnico.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegistroUnicoActivity.class);
            startActivity(intent);
        });
    }
}