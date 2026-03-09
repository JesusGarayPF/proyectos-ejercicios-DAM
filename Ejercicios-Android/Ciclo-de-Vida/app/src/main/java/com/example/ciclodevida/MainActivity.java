package com.example.ciclodevida;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    /**
     * Método invocado por Android cuando lanza la actividad.
     * Inicializamos el interfaz grá
     *
     * @param savedInstanceState Lo ignoramos.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo_de_vida);
        android.util.Log.i(TAG, "onCreate");
    }

    //--------------------------------------------------

    /**
     * Método llamado cuando la actividad pasa de estar
     oculta a
     * estar, al menos, parcialmente visible.
     */
    protected void onStart() {
        super.onStart();
        android.util.Log.i(TAG, "onStart");
    }

    //---------------------------------------

    /**
     * Método llamado cuando s
     después
     Vamos a hacer una nueva aplicación que nos muestre un mensaje cuando
     invoque a los métodos que controlan el ciclo de vida.
     Crea un nuevo proyecto Android:
     Application Name: Ciclo de vida
     Project name: CicloDeVida
     public class CicloDeVida extends Activity {
     todo invocado por Android cuando lanza la actividad.
     * Inicializamos el interfaz gráfico.
     * @param //savedInstanceState Lo ignoramos.
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_ciclo_de_vida);
    android.util.Log.i(TAG, "onCreate"); --------------------------------------------------
    todo llamado cuando la actividad pasa de estar
     * estar, al menos, parcialmente visible.
    protected void onStart() {
    super.onStart();
    android.util.Log.i(TAG, "onStart"); --------------------------------------------------
    todo llamado cuando se recupera una actividad
    Vamos a hacer una nueva aplicación que nos muestre un mensaje cuando se

    todo invocado por Android cuando lanza la actividad.
    protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_ciclo_de_vida); ---------------------------------------------------
    todo llamado cuando la actividad pasa de estar -----------
    e recupera una actividad
     * de haber estado detenida.
     */
    protected void onRestart() {
        super.onRestart();
        android.util.Log.i(TAG, "onRestart");
    }

    //---------------------------------------------------

    /**
     * Método llamado cuando la actividad pasa a primer
     plano.
     */
    protected void onResume() {
        super.onResume();
        android.util.Log.i(TAG, "onResume");
    }

    //---------------------------------------------------

    /**
     * Método llamado cuando la actividad deja de estar
     * en primer plano (puede que sigua siendo parcialmente
     * visible).
     */
    protected void onPause() {
        super.onPause();
        android.util.Log.i(TAG, "onPause");
    }

    //---------------------------------------------------

    /**
     * Método llamado cuando la actividad se detiene
     * (deja de estar visible).
     */
    protected void onStop() {
        super.onStop();
        android.util.Log.i(TAG, "onStop");
    }

    //---------------------------------------------------

    /**
     * Método llamado antes de que la aplicación se destruya.
     */
    protected void onDestroy() {
        super.onDestroy();
        android.util.Log.i(TAG, "onDestroy");
    }

    //---------------------------------------------------
    //            Atributos protegidos/privados
    //---------------------------------------------------

    /**
     * Constante con la etiqueta que nos identificará en el
     * logcat.
     */
    private static final String TAG = "CicloDeVida";
}