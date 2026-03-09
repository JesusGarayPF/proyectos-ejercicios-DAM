package dm2e.jesus.parejascartas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PuntuacionesActivity extends AppCompatActivity {

    private TextView tvPuntuaciones;
    private Button btnVolverAJugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuaciones);

        tvPuntuaciones = findViewById(R.id.tvPuntuaciones);
        btnVolverAJugar = findViewById(R.id.btnVolverAJugar);

        DatabaseHelper db = new DatabaseHelper(this);
        String nombreJugador = getIntent().getStringExtra("nombreJugador");
        int puntuacionActual = getIntent().getIntExtra("puntuacionActual", -1);

        // Mostrar puntuación actual y almacenada
        StringBuilder puntuacionesTexto = new StringBuilder();
        if (nombreJugador != null) {
            puntuacionesTexto.append("Jugador: ").append(nombreJugador).append("\n");
            if (puntuacionActual != -1) {
                puntuacionesTexto.append("Puntuación Actual: ").append(puntuacionActual).append("\n");
            }
            int puntuacionGuardada = db.obtenerPuntuacion(nombreJugador);
            puntuacionesTexto.append("Mejor Puntuación: ").append(puntuacionGuardada).append("\n");
        } else {
            puntuacionesTexto.append("No se encontró el nombre del jugador.");
        }
        tvPuntuaciones.setText(puntuacionesTexto.toString());
        btnVolverAJugar.setOnClickListener(v -> {
            Intent intent = new Intent(PuntuacionesActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
