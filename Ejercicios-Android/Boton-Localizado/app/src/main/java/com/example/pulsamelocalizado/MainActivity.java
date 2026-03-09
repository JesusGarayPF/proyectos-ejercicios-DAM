package com.example.pulsamelocalizado;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    //---------------------------------------------------
    //            Atributos protegidos/privados
    //---------------------------------------------------
    /**
     * Constante con el "tag" usado para registro de sucesos
     * en LogCat relativos a esta actividad.
     */
    private static final String TAG = "Pulsame";
    /**
     * Botón de la ventana.
     */
    private Button _boton;

    /**
     * Número de veces que se ha pulsado el botón.
     */
    private int _numVeces;

    /**
     * Método llamado cuando se crea la actividad.
     *
     * @param savedInstanceState Ignoramos el parámetro.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Llamamos al método que estamos sobreescribiendo
        // de la clase padre.
        super.onCreate(savedInstanceState);

        // Configuramos la ventana, añadiendo un botón
        // que llamará a nuestro método protegido.
        _boton = new Button(this);
        _boton.setText(R.string.pulsameAdmiracion);
        _boton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        botonPulsado();
                    }
                }
        );
        // Ponemos el botón como componente de la
        // actividad.
        setContentView(_boton);

    } // onCreate

    //---------------------------------------------------

    /**
     * Método llamado cuando se pulsa sobre el botón
     * de la ventana. Es llamado a través de la clase
     * anónima del evento.
     */
    private void botonPulsado() {

        // Incrementamos el contador...
        ++_numVeces;

        // ... y actualizamos la etiqueta del botón.
        _boton.setText("Pulsado " + _numVeces + " veces");
        System.out.println("Pulsado " + _numVeces + " veces");
        android.util.Log.d(TAG, "botonPulsado " + _numVeces);
    } // botonPulsado

}