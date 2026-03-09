package dm2e.jesus.faunaiberica;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DatosAnimales extends AppCompatActivity {

    private TextView tvDescripcion;
    private ImageView imageViewAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_animales);

        tvDescripcion = findViewById(R.id.tvDescripcion);
        imageViewAnimal = findViewById(R.id.imageViewAnimal);

        String animalName = getIntent().getStringExtra("animalName");
        cargarDatosAnimal(animalName);
    }

    private void cargarDatosAnimal(String animalName) {
        int rawFileId;
        int imageResource;

        switch (animalName) {
            case "Lobo Ibérico":
                rawFileId = R.raw.lobo;
                imageResource = R.drawable.rbloboiberico;
                break;
            case "Halcón Peregrino":
                rawFileId = R.raw.halcon;
                imageResource = R.drawable.rbhalconperegrino;
                break;
            case "Lince":
                rawFileId = R.raw.lince;
                imageResource = R.drawable.rblinceiberico;
                break;
            case "Águila Imperial":
                rawFileId = R.raw.aguila;
                imageResource = R.drawable.rbaguilaimperial;
                break;
            default:
                rawFileId = R.raw.error;
                imageResource = R.drawable.rboso;
                break;
        }

        try {
            InputStream inputStream = getResources().openRawResource(rawFileId);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder description = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                description.append(line).append("\n");
            }

            inputStream.close();
            tvDescripcion.setText(description.toString());
            tvDescripcion.setVisibility(View.VISIBLE);
            imageViewAnimal.setImageResource(imageResource);

        } catch (Exception e) {
            tvDescripcion.setText("No se pudo cargar la descripción del animal.");
            tvDescripcion.setVisibility(View.VISIBLE);
            tvDescripcion.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            imageViewAnimal.setImageResource(R.drawable.rboso);
            e.printStackTrace();
        }
    }
}
