package dm2e.jesus.parejascartas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class CartasActivity extends AppCompatActivity {

    private TextView tvJugador;
    private GridLayout glCartas;
    private ArrayList<Integer> cartas;
    private int pulsaciones = 0;
    private int nivel = 1;
    private String nombreJugador;
    private ImageButton primeraCarta = null;
    private int valorPrimeraCarta = -1;
    private boolean[] estadoCartas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartas);

        tvJugador = findViewById(R.id.tvJugador);
        glCartas = findViewById(R.id.glCartas);
        nombreJugador = getIntent().getStringExtra("nombreJugador");

        if (savedInstanceState != null) {
            nivel = savedInstanceState.getInt("nivel");
            pulsaciones = savedInstanceState.getInt("pulsaciones");
            estadoCartas = savedInstanceState.getBooleanArray("estadoCartas");
            cartas = savedInstanceState.getIntegerArrayList("cartas");
            valorPrimeraCarta = savedInstanceState.getInt("valorPrimeraCarta", -1);

            iniciarNivel(nivel);

            int indicePrimeraCarta = savedInstanceState.getInt("indicePrimeraCarta", -1);
            if (indicePrimeraCarta != -1) {
                primeraCarta = (ImageButton) glCartas.getChildAt(indicePrimeraCarta);
            }
        } else {
            iniciarNivel(nivel);
        }
        actualizarJugadorIntentos();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("nivel", nivel);
        outState.putInt("pulsaciones", pulsaciones);
        outState.putBooleanArray("estadoCartas", estadoCartas);
        outState.putIntegerArrayList("cartas", cartas);
        outState.putInt("valorPrimeraCarta", valorPrimeraCarta);
        if (primeraCarta != null) {
            outState.putInt("indicePrimeraCarta", obtenerIndicePrimeraCarta());
        } else {
            outState.putInt("indicePrimeraCarta", -1);
        }
    }

    private void iniciarNivel(int nivel) {
        glCartas.removeAllViews();

        if (cartas == null || cartas.isEmpty()) {
            cartas = generarCartas(nivel);
            estadoCartas = new boolean[cartas.size()];
            for (int i = 0; i < estadoCartas.length; i++) {
                estadoCartas[i] = false;
            }
        }

        int columnas = (nivel == 1) ? 2 : (nivel == 2) ? 3 : 4;
        glCartas.setColumnCount(columnas);

        // Detectar orientación y usar dimensiones adecuadas
        boolean isLandscape = getResources().getConfiguration().orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE;

        int tamañoAncho, tamañoAlto;
        if (nivel == 1) {
            tamañoAncho = getResources().getDimensionPixelSize(isLandscape ? R.dimen.carta_nivel_1_landscape_ancho : R.dimen.carta_nivel_1_ancho);
            tamañoAlto = getResources().getDimensionPixelSize(isLandscape ? R.dimen.carta_nivel_1_landscape_alto : R.dimen.carta_nivel_1_alto);
        } else if (nivel == 2) {
            tamañoAncho = getResources().getDimensionPixelSize(isLandscape ? R.dimen.carta_nivel_2_landscape_ancho : R.dimen.carta_nivel_2_ancho);
            tamañoAlto = getResources().getDimensionPixelSize(isLandscape ? R.dimen.carta_nivel_2_landscape_alto : R.dimen.carta_nivel_2_alto);
        } else {
            tamañoAncho = getResources().getDimensionPixelSize(isLandscape ? R.dimen.carta_nivel_3_landscape_ancho : R.dimen.carta_nivel_3_ancho);
            tamañoAlto = getResources().getDimensionPixelSize(isLandscape ? R.dimen.carta_nivel_3_landscape_alto : R.dimen.carta_nivel_3_alto);
        }
        //Hacer visible las cartas obtenidas
        for (int i = 0; i < cartas.size(); i++) {
            View carta = generarCarta(this, cartas.get(i), i, tamañoAncho, tamañoAlto);
            glCartas.addView(carta);

            // Restaurar cartas emparejadas
            if (estadoCartas[i]) {
                ImageButton imgCarta = (ImageButton) glCartas.getChildAt(i);
                imgCarta.setImageResource(obtenerImagenCarta(cartas.get(i)));
                imgCarta.setBackgroundColor(Color.YELLOW);
            }
        }
    }

    private ArrayList<Integer> generarCartas(int nivel) {
        ArrayList<Integer> todasLasCartas = new ArrayList<>();
        for (int i = 1; i <= 40; i++) {
            todasLasCartas.add(i);
        }
        Collections.shuffle(todasLasCartas);
        int pares = (nivel == 1) ? 4 : (nivel == 2) ? 6 : 12;
        ArrayList<Integer> cartasSeleccionadas = new ArrayList<>();
        for (int i = 0; i < pares / 2; i++) {
            int carta = todasLasCartas.get(i);
            cartasSeleccionadas.add(carta);
            cartasSeleccionadas.add(carta);
        }
        Collections.shuffle(cartasSeleccionadas);
        return cartasSeleccionadas;
    }


    private View generarCarta(Context context, int valor, int index, int tamañoAncho, int tamañoAlto) {
        ImageButton carta = new ImageButton(context);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = tamañoAncho;
        params.height = tamañoAlto;
        carta.setLayoutParams(params);
        carta.setImageResource(R.drawable.trasera);
        carta.setScaleType(ImageButton.ScaleType.FIT_XY);
        carta.setBackground(null);
        carta.setOnClickListener(v -> {
            if (estadoCartas[index]) return;

            carta.setImageResource(obtenerImagenCarta(valor));
            estadoCartas[index] = true;
            manejarVolteo(carta, valor, index);
        });
        return carta;
    }

    private int obtenerImagenCarta(int valor) {
        switch (valor) {
            // Bastos
            case 1:
                return R.drawable.asbastos;
            case 2:
                return R.drawable.dosbastos;
            case 3:
                return R.drawable.tresbastos;
            case 4:
                return R.drawable.cuatrobastos;
            case 5:
                return R.drawable.cincobastos;
            case 6:
                return R.drawable.seisbastos;
            case 7:
                return R.drawable.sietebastos;
            case 8:
                return R.drawable.sotabastos;
            case 9:
                return R.drawable.caballobastos;
            case 10:
                return R.drawable.reybastos;
            // Copas
            case 11:
                return R.drawable.ascopas;
            case 12:
                return R.drawable.doscopas;
            case 13:
                return R.drawable.trescopas;
            case 14:
                return R.drawable.cuatrocopas;
            case 15:
                return R.drawable.cincocopas;
            case 16:
                return R.drawable.seiscopas;
            case 17:
                return R.drawable.sietecopas;
            case 18:
                return R.drawable.sotacopas;
            case 19:
                return R.drawable.caballocopas;
            case 20:
                return R.drawable.reycopas;
            // Espadas
            case 21:
                return R.drawable.asespadas;
            case 22:
                return R.drawable.dosespadas;
            case 23:
                return R.drawable.tresespadas;
            case 24:
                return R.drawable.cuatroespadas;
            case 25:
                return R.drawable.cincoespadas;
            case 26:
                return R.drawable.seisespadas;
            case 27:
                return R.drawable.sieteespadas;
            case 28:
                return R.drawable.sotaespadas;
            case 29:
                return R.drawable.caballoespadas;
            case 30:
                return R.drawable.reyespadas;
            // Oros
            case 31:
                return R.drawable.asoros;
            case 32:
                return R.drawable.dosoros;
            case 33:
                return R.drawable.tresoros;
            case 34:
                return R.drawable.cuatrooros;
            case 35:
                return R.drawable.cincooros;
            case 36:
                return R.drawable.seisoros;
            case 37:
                return R.drawable.sieteoros;
            case 38:
                return R.drawable.sotaoros;
            case 39:
                return R.drawable.caballooros;
            case 40:
                return R.drawable.reyoros;
            default:
                return R.drawable.trasera;
        }
    }

    private void manejarVolteo(ImageButton carta, int valor, int index) {
        pulsaciones++;
        actualizarJugadorIntentos();
        if (primeraCarta == null) {
            primeraCarta = carta;
            valorPrimeraCarta = valor;
        } else {
            if (valor == valorPrimeraCarta) {
                // Si las cartas coinciden
                carta.setBackgroundColor(Color.YELLOW);
                primeraCarta.setBackgroundColor(Color.YELLOW);
                primeraCarta = null;

                MediaPlayer mp = MediaPlayer.create(this, R.raw.aplausos);
                mp.start();

                verificarCompletarNivel();
            } else {
                // Si no coinciden
                carta.postDelayed(() -> {
                    carta.setImageResource(R.drawable.trasera);
                    primeraCarta.setImageResource(R.drawable.trasera);
                    estadoCartas[index] = false;
                    estadoCartas[obtenerIndicePrimeraCarta()] = false;
                    primeraCarta = null;
                }, 800);
            }
        }
    }

    private int obtenerIndicePrimeraCarta() {
        for (int i = 0; i < glCartas.getChildCount(); i++) {
            if (glCartas.getChildAt(i) == primeraCarta) {
                return i;
            }
        }
        return -1;
    }

    private void verificarCompletarNivel() {
        boolean todasVolteadas = true;
        for (int i = 0; i < glCartas.getChildCount(); i++) {
            ImageButton carta = (ImageButton) glCartas.getChildAt(i);
            if (carta.getBackground() == null) {
                todasVolteadas = false;
                break;
            }
        }
        if (todasVolteadas) {
            Toast.makeText(this, "¡Nivel completado!", Toast.LENGTH_SHORT).show();
            if (nivel == 3) { // Si es el último nivel
                guardarPuntuacion();
                Intent intent = new Intent(CartasActivity.this, PuntuacionesActivity.class);
                intent.putExtra("nombreJugador", nombreJugador);
                intent.putExtra("puntuacionActual", pulsaciones);
                startActivity(intent);
                finish();
            } else { // Avanzar al siguiente nivel
                nivel++;
                cartas = null; // Reiniciar las cartas
                estadoCartas = null; // Reiniciar el estado de las cartas
                primeraCarta = null; // Eliminar referencia de la primera carta
                valorPrimeraCarta = -1; // Reiniciar el valor de la primera carta
                iniciarNivel(nivel);
            }
        }
    }

    private void actualizarJugadorIntentos() {
        tvJugador.setText("Jugador: " + nombreJugador + " | Intentos: " + pulsaciones);
    }

    private void guardarPuntuacion() {
        DatabaseHelper db = new DatabaseHelper(this);
        int puntuacionActual = pulsaciones;
        if (db.existeJugador(nombreJugador)) {
            int puntuacionGuardada = db.obtenerPuntuacion(nombreJugador);
            if (puntuacionActual < puntuacionGuardada) {
                db.actualizarPuntuacion(nombreJugador, puntuacionActual);
            }
        } else {
            db.insertarJugador(nombreJugador, puntuacionActual);
        }
    }
}
