package Procesos.CreacionProcesos.PracticaSuperComputador;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.Semaphore;

public class Supercomputador {
    private int núcleosDisponibles;
    private int ramDisponible;
    private AtomicInteger servidos = new AtomicInteger(0);
    private Semaphore acceso;
    private final int maxUsuarios;

    public Supercomputador(int maxUsuarios, int núcleos, int ram) {
        this.maxUsuarios = maxUsuarios;
        this.núcleosDisponibles = núcleos;
        this.ramDisponible = ram;
        this.acceso = new Semaphore(maxUsuarios);
    }

    public void entrar() throws InterruptedException {
        acceso.acquire();
    }

    public void salir() {
        acceso.release();
    }

    public synchronized void obtenerMV(int núcleos, int ram) throws InterruptedException {
        while (núcleos > núcleosDisponibles || ram > ramDisponible) {
            wait();
        }
        núcleosDisponibles -= núcleos;
        ramDisponible -= ram;
    }

    public synchronized void devolverMV(int núcleos, int ram) {
        núcleosDisponibles += núcleos;
        ramDisponible += ram;
        servidos.incrementAndGet();
        notifyAll();
    }

    public int núcleosDisponibles() {
        return núcleosDisponibles;
    }

    public int ramDisponible() {
        return ramDisponible;
    }

    public int usuariosActuales() {
        return maxUsuarios - acceso.availablePermits();
    }

    public int usuariosServidos() {
        return servidos.get();
    }
}


