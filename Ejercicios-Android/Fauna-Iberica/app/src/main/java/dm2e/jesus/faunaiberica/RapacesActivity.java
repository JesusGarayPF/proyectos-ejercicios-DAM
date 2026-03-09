package dm2e.jesus.faunaiberica;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class RapacesActivity extends AppCompatActivity {

    private RadioButton rbAguilaImperial, rbBuhoReal, rbHalconPeregrino, rbCernicalo, rbQuebrantahuesos, rbBuitreLeonado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rapaces);

        rbAguilaImperial = findViewById(R.id.rbAguilaImperial);
        rbBuhoReal = findViewById(R.id.rbBuhoReal);
        rbHalconPeregrino = findViewById(R.id.rbHalconPeregrino);
        rbCernicalo = findViewById(R.id.rbCernicalo);
        rbQuebrantahuesos = findViewById(R.id.rbQuebrantahuesos);
        rbBuitreLeonado = findViewById(R.id.rbBuitreLeonado);

        setRadioButtonClickListeners();
    }

    private void setRadioButtonClickListeners() {
        View.OnClickListener listener = view -> {
            clearAllRadioButtons();
            ((RadioButton) view).setChecked(true);

            String animalName = "";
            if (view.getId() == R.id.rbAguilaImperial) {
                animalName = "Águila Imperial";
            } else if (view.getId() == R.id.rbBuhoReal) {
                animalName = "Búho Real";
            } else if (view.getId() == R.id.rbHalconPeregrino) {
                animalName = "Halcón Peregrino";
            } else if (view.getId() == R.id.rbCernicalo) {
                animalName = "Cernícalo";
            } else if (view.getId() == R.id.rbQuebrantahuesos) {
                animalName = "Quebrantahuesos";
            } else if (view.getId() == R.id.rbBuitreLeonado) {
                animalName = "Buitre Leonado";
            }

            Intent intent = new Intent(RapacesActivity.this, DatosAnimales.class);
            intent.putExtra("animalName", animalName);
            startActivity(intent);
        };

        rbAguilaImperial.setOnClickListener(listener);
        rbBuhoReal.setOnClickListener(listener);
        rbHalconPeregrino.setOnClickListener(listener);
        rbCernicalo.setOnClickListener(listener);
        rbQuebrantahuesos.setOnClickListener(listener);
        rbBuitreLeonado.setOnClickListener(listener);
    }

    private void clearAllRadioButtons() {
        rbAguilaImperial.setChecked(false);
        rbBuhoReal.setChecked(false);
        rbHalconPeregrino.setChecked(false);
        rbCernicalo.setChecked(false);
        rbQuebrantahuesos.setChecked(false);
        rbBuitreLeonado.setChecked(false);
    }
}
