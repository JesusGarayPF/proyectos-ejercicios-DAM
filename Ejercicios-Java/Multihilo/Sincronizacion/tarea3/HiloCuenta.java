package Procesos.CreacionProcesos.tarea3;

public class HiloCuenta implements Runnable {
    private final ContarSincronizado contador;

    public HiloCuenta(ContarSincronizado contador) {
        this.contador = contador;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            contador.incrementar();
            System.out.println("Hilo " + Thread.currentThread().getName() + " incrementó a: " + contador.getValor());
        }
    }
}

