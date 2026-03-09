package dm2e.jesus.tragaperras;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int pulsaciones = 0;
    private int puntuacion = 0;
    private TextView tvPulsaciones;
    private TextView tvPuntuacion;
    private ImageView imageViewSlot, imageViewSlot2, imageViewSlot3;
    private int[] imagenesFrutas = {
            R.drawable.cereza,
            R.drawable.fresa,
            R.drawable.granada,
            R.drawable.limon,
            R.drawable.manzana,
            R.drawable.melocoton,
            R.drawable.melon,
            R.drawable.naranja,
            R.drawable.pera,
            R.drawable.pina,
            R.drawable.platano,
            R.drawable.sandia,
            R.drawable.tomate,
            R.drawable.uvas
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvPulsaciones = findViewById(R.id.tv_pulsaciones);
        tvPuntuacion = findViewById(R.id.tv_puntuacion);
        imageViewSlot = findViewById(R.id.imageView_slot);
        imageViewSlot2 = findViewById(R.id.imageView_slot2);
        imageViewSlot3 = findViewById(R.id.imageView_slot3);
        Button btnStart = findViewById(R.id.btn_start);
        Button btnStop = findViewById(R.id.btn_stop);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageViewSlot.setImageResource(R.drawable.slot_animation);
                imageViewSlot2.setImageResource(R.drawable.slot_animation2);
                imageViewSlot3.setImageResource(R.drawable.slot_animation3);
                imageViewSlot.post(new Runnable() {
                    @Override
                    public void run() {
                        AnimationDrawable slotAnimation = (AnimationDrawable) imageViewSlot.getDrawable();
                        AnimationDrawable slotAnimation2 = (AnimationDrawable) imageViewSlot2.getDrawable();
                        AnimationDrawable slotAnimation3 = (AnimationDrawable) imageViewSlot3.getDrawable();

                        if (slotAnimation != null) slotAnimation.start();
                        if (slotAnimation2 != null) slotAnimation2.start();
                        if (slotAnimation3 != null) slotAnimation3.start();
                    }
                });
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationDrawable slotAnimation = (AnimationDrawable) imageViewSlot.getDrawable();
                AnimationDrawable slotAnimation2 = (AnimationDrawable) imageViewSlot2.getDrawable();
                AnimationDrawable slotAnimation3 = (AnimationDrawable) imageViewSlot3.getDrawable();
                if (slotAnimation != null) slotAnimation.stop();
                if (slotAnimation2 != null) slotAnimation2.stop();
                if (slotAnimation3 != null) slotAnimation3.stop();
                int resultado1 = imagenesFrutas[new Random().nextInt(imagenesFrutas.length)];
                int resultado2 = imagenesFrutas[new Random().nextInt(imagenesFrutas.length)];
                int resultado3 = imagenesFrutas[new Random().nextInt(imagenesFrutas.length)];
                imageViewSlot.setImageResource(resultado1);
                imageViewSlot2.setImageResource(resultado2);
                imageViewSlot3.setImageResource(resultado3);
                if (resultado1 == resultado2 && resultado2 == resultado3) {
                    puntuacion += 10;
                } else if (resultado1 == resultado2 || resultado1 == resultado3 || resultado2 == resultado3) {
                    puntuacion += 3;
                }
                pulsaciones++;
                updateTexts();
            }
        });
    }

    private void updateTexts() {
        String pulsacionesText = getResources().getQuantityString(R.plurals.pulsaciones, pulsaciones, pulsaciones);
        String puntuacionText = getResources().getQuantityString(R.plurals.puntos, puntuacion, puntuacion);

        tvPulsaciones.setText(pulsacionesText);
        tvPuntuacion.setText(puntuacionText);
    }
}
