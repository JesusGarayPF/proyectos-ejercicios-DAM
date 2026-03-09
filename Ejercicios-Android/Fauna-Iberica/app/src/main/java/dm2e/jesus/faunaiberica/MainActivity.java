package dm2e.jesus.faunaiberica;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RadioButton rbCarnivoros, rbRapaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rbCarnivoros = findViewById(R.id.rbCarnivoros);
        rbRapaces = findViewById(R.id.rbRapaces);

        rbCarnivoros.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CarnivorosActivity.class);
            startActivity(intent);
        });

        rbRapaces.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RapacesActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        clearAllRadioButtons();
    }

    private void clearAllRadioButtons() {
        rbCarnivoros.setChecked(false);
        rbRapaces.setChecked(false);
    }
}
