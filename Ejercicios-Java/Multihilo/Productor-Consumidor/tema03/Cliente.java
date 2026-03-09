package Procesos.CreacionProcesos.tema03;

public class Cliente implements Runnable {
    Bar bar;
    int cañas;

    public Cliente(Bar bar, int cañas) {
        this.bar = bar;
        this.cañas = cañas;
    }

    public void run() {
        bar.entrar();
        for (int i = 0; i < cañas; i++) {
            bar.tomarCaña();
        }
        bar.salir();
    }
}
