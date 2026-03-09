package dm2e.jesus.adivinaminumerosave;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int numeroSecreto;
    private int numIntentos;
    private String mensajePista;

    private static final String STATE_NUM_SECRETO = "numSecreto";
    private static final String STATE_NUM_INTENTOS = "numIntentos";
    private static final String STATE_MENSAJE_PISTA = "mensajePista";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tvMensaje = findViewById(R.id.tvMensaje);
        final TextView tvPista = findViewById(R.id.tvPista);
        final EditText etNumero = findViewById(R.id.etNumero);
        Button btnProbar = findViewById(R.id.btnProbar);

        if (savedInstanceState == null) {
            iniciarNuevaPartida(tvMensaje, tvPista);
        } else {
            numeroSecreto = savedInstanceState.getInt(STATE_NUM_SECRETO);
            numIntentos = savedInstanceState.getInt(STATE_NUM_INTENTOS);
            mensajePista = savedInstanceState.getString(STATE_MENSAJE_PISTA);
            tvPista.setText(mensajePista);
            tvMensaje.setText("Llevas " + numIntentos + " intentos.");
        }

        btnProbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int numeroIngresado = Integer.parseInt(etNumero.getText().toString());

                    if (numeroIngresado == numeroSecreto) {
                        mensajePista = "¡Correcto! Has adivinado el número.";
                        tvPista.setText(mensajePista);
                        Toast.makeText(MainActivity.this, "¡Has ganado! Se generará un nuevo número.", Toast.LENGTH_SHORT).show();
                        iniciarNuevaPartida(tvMensaje, tvPista);
                        etNumero.setText("");
                    } else {
                        numIntentos++;
                        if (numeroIngresado < numeroSecreto) {
                            mensajePista = "El número es mayor que " + numeroIngresado + ". ¡Inténtalo de nuevo!";
                        } else {
                            mensajePista = "El número es menor que " + numeroIngresado + ". ¡Inténtalo de nuevo!";
                        }
                        tvPista.setText(mensajePista);
                        tvMensaje.setText("Llevas " + numIntentos + " intentos.");
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Por favor ingresa un número válido.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_NUM_SECRETO, numeroSecreto);
        outState.putInt(STATE_NUM_INTENTOS, numIntentos);
        outState.putString(STATE_MENSAJE_PISTA, mensajePista);
    }

    private void iniciarNuevaPartida(TextView tvMensaje, TextView tvPista) {
        numeroSecreto = new Random().nextInt(100) + 1;
        numIntentos = 0;
        mensajePista = "";
        tvPista.setText("");
        tvMensaje.setText("He pensado un número entre 1 y 100.\n¡Adivina cuál es!");
    }
}
