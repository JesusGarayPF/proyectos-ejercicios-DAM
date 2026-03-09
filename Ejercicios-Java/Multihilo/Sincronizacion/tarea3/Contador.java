package Procesos.CreacionProcesos.tarea3;

public class Contador {
    private final ContarSincronizado contar;

    public Contador() {
        contar = new ContarSincronizado();
    }

    public void iniciarHilos() throws InterruptedException {
        Thread[] hilos = new Thread[10];

        for (int i = 0; i < 10; i++) {
            hilos[i] = new Thread(new HiloCuenta(contar));
            hilos[i].start();
        }

        for (Thread hilo : hilos) {
            hilo.join();
        }

        System.out.println("Valor final del contador: " + contar.getValor());
    }

    public static void main(String[] args) throws InterruptedException {
        Contador contador = new Contador();
        contador.iniciarHilos();
    }
}


