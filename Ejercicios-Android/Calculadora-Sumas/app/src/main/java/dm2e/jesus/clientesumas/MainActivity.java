package dm2e.jesus.clientesumas;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private static final String SERVER_IP = "192.168.1.129";
    private static final int SERVER_PORT = 9898;
    private ClienteSumas cliente;

    private TextView resultado;
    private TextView estadoRed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultado = findViewById(R.id.resultado);
        estadoRed = findViewById(R.id.estadoRed);

        cliente = new ClienteSumas();
        cliente.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cliente != null) {
            cliente.interrupt();
            cliente = null;
        }
    }

    class ClienteSumas extends Thread {
        @Override
        public void run() {
            while (!isInterrupted()) {
                try {
                    runOnUiThread(() -> estadoRed.setText("Conectando..."));

                    Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                    PrintStream ps = new PrintStream(socket.getOutputStream());
                    Scanner sc = new Scanner(socket.getInputStream());

                    int a = (int) (Math.random() * 100);
                    int b = (int) (Math.random() * 100);
                    ps.println(a + " " + b);

                    String resultadoSuma = sc.next();
                    runOnUiThread(() -> resultado.setText(a + " + " + b + " = " + resultadoSuma));

                    socket.close();
                    sc.close();
                    ps.close();

                    Thread.sleep(1000);
                } catch (Exception e) {
                    runOnUiThread(() -> estadoRed.setText("Error: " + e.getMessage()));
                    interrupt();
                }
            }
        }
    }
}
