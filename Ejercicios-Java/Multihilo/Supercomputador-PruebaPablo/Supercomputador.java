package Procesos.CreacionProcesos.pruebaPablo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Supercomputador {

    private final int maxUsuarios;
    private final Semaphore acceso;
    private int nucleosDisponibles;
    private int ramDisponible;
    private final AtomicInteger servidos;

    public Supercomputador(int maxUsuarios, int nucleos, int ram) {
        this.maxUsuarios = maxUsuarios;
        this.nucleosDisponibles = nucleos;
        this.ramDisponible = ram;
        this.acceso = new Semaphore(maxUsuarios, true);
        this.servidos = new AtomicInteger(0);
    }

    public void entrar() throws InterruptedException {
        acceso.acquire();
    }

    public void salir() {
        acceso.release();
    }

    public synchronized void obtenerMV(int nucleos, int ram) throws InterruptedException {
        while (nucleos > nucleosDisponibles || ram > ramDisponible) {
            wait();
        }
        nucleosDisponibles -= nucleos;
        ramDisponible -= ram;
    }

    public synchronized void devolverMV(int nucleos, int ram) {
        nucleosDisponibles += nucleos;
        ramDisponible += ram;
        servidos.incrementAndGet();
        notifyAll();
    }

    public synchronized int getNucleosDisponibles() {
        return nucleosDisponibles;
    }

    public synchronized int getRamDisponible() {
        return ramDisponible;
    }

    public synchronized int getUsuariosServidos() {
        return servidos.get();
    }

    public synchronized int getUsuariosActuales() {
        return maxUsuarios - acceso.availablePermits();
    }
}