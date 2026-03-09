package dm2e.jesus.pruebaxiami;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView preguntaTextView;
    private RadioGroup grupoRadioRegiones;
    private Button botonComprobar;

    private List<String> regiones = Arrays.asList(
            "Andalucía", "Aragón", "Asturias", "Cantabria", "Castilla y León", "Castilla-La Mancha",
            "Cataluña", "Comunidad Valenciana", "Extremadura", "Galicia", "Madrid", "Murcia",
            "Navarra", "País Vasco", "La Rioja", "Islas Baleares", "Islas Canarias"
    );

    private String regionActual = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preguntaTextView = findViewById(R.id.questionTextView);
        grupoRadioRegiones = findViewById(R.id.regionRadioGroup);
        botonComprobar = findViewById(R.id.checkButton);

        seleccionarRegionAleatoria();

        botonComprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprobarRespuesta();
            }
        });
    }

    private void seleccionarRegionAleatoria() {
        Random random = new Random();
        regionActual = regiones.get(random.nextInt(regiones.size()));
        preguntaTextView.setText("¿Dónde se encuentra la región de " + regionActual + "?");
    }

    private void comprobarRespuesta() {
        int idRadioSeleccionado = grupoRadioRegiones.getCheckedRadioButtonId();
        if (idRadioSeleccionado != -1) {
            RadioButton radioSeleccionado = findViewById(idRadioSeleccionado);
            String regionSeleccionada = radioSeleccionado.getTag().toString(); // Usa el tag en lugar del texto
            if (regionSeleccionada.equals(regionActual)) {
                mostrarAlerta("¡Correcto!", "Has acertado.");
            } else {
                mostrarAlerta("Error", "Respuesta incorrecta. La respuesta correcta es " + regionActual + ".");
            }
            seleccionarRegionAleatoria();
            grupoRadioRegiones.clearCheck();
        } else {
            mostrarAlerta("Advertencia", "Por favor, selecciona una opción.");
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        new AlertDialog.Builder(this)
                .setTitle(titulo)
                .setMessage(mensaje)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
