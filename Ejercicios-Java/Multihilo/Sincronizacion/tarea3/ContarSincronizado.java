package Procesos.CreacionProcesos.tarea3;

public class ContarSincronizado {
    private int valor;
    public synchronized void incrementar() {
        valor++;
    }
    public synchronized int getValor() {
        return valor;
    }
}
