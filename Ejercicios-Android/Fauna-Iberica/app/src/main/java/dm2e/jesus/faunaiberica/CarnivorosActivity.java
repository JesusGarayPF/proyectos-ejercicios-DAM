package dm2e.jesus.faunaiberica;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class CarnivorosActivity extends AppCompatActivity {

    private RadioButton rbLoboIberico, rbGineta, rbZorro, rbLinceIberico, rbNutriaIberica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carnivoros);
        rbLoboIberico = findViewById(R.id.rbLoboIberico);
        rbGineta = findViewById(R.id.rbGineta);
        rbZorro = findViewById(R.id.rbZorro);
        rbLinceIberico = findViewById(R.id.rbLinceIberico);
        rbNutriaIberica = findViewById(R.id.rbNutriaIberica);

        setRadioButtonClickListeners();
    }

    private void setRadioButtonClickListeners() {
        View.OnClickListener listener = view -> {
            clearAllRadioButtons();
            ((RadioButton) view).setChecked(true);

            String animalName = "";
            if (view.getId() == R.id.rbLoboIberico) {
                animalName = "Lobo Ibérico";
            } else if (view.getId() == R.id.rbGineta) {
                animalName = "Gineta";
            } else if (view.getId() == R.id.rbZorro) {
                animalName = "Zorro";
            } else if (view.getId() == R.id.rbLinceIberico) {
                animalName = "Lince Ibérico";
            } else if (view.getId() == R.id.rbNutriaIberica) {
                animalName = "Nutria Ibérica";
            }

            Intent intent = new Intent(CarnivorosActivity.this, DatosAnimales.class);
            intent.putExtra("animalName", animalName);
            startActivity(intent);
        };

        rbLoboIberico.setOnClickListener(listener);
        rbGineta.setOnClickListener(listener);
        rbZorro.setOnClickListener(listener);
        rbLinceIberico.setOnClickListener(listener);
        rbNutriaIberica.setOnClickListener(listener);
    }

    private void clearAllRadioButtons() {
        rbLoboIberico.setChecked(false);
        rbGineta.setChecked(false);
        rbZorro.setChecked(false);
        rbLinceIberico.setChecked(false);
        rbNutriaIberica.setChecked(false);
    }
}
