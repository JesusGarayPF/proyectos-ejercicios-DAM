package Procesos.CreacionProcesos.tema03;

public class Camarero implements Runnable {
    Bar bar;

    public Camarero(Bar bar) {
        this.bar = bar;
    }

    public void run() {
        while (true) {
            bar.reponer();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

