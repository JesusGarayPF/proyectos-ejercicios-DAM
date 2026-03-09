package Procesos.CreacionProcesos.tema03;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bar {
    private int aforoMaximo;
    private int clientesDentro = 0;
    private int nCañas;
    private int cañasServidas = 0;
    private Lock lock = new ReentrantLock();
    private Condition puedeEntrar = lock.newCondition();
    private Condition puedeSalir = lock.newCondition();
    private Condition barrilVacio = lock.newCondition();
    private boolean barrilReponiendo = false;

    public Bar(int aforo, int nCañas) {
        this.aforoMaximo = aforo;
        this.nCañas = nCañas;
    }

    public void entrar() {
        lock.lock();
        try {
            while (clientesDentro >= aforoMaximo) {
                puedeEntrar.await();
            }
            clientesDentro++;
            System.out.println("Cliente ha entrado. Clientes dentro: " + clientesDentro);
            puedeSalir.signal();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public void salir() {
        lock.lock();
        try {
            if (clientesDentro > 0) {
                clientesDentro--;
                System.out.println("Cliente ha salido. Clientes dentro: " + clientesDentro);
                puedeEntrar.signal();
                if (clientesDentro == 0) {
                    barrilVacio.signalAll();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void tomarCaña() {
        lock.lock();
        try {
            while (cañasServidas >= nCañas) {
                System.out.println("Barril vacío. Esperar al cambio de barril.");
                barrilVacio.await();
            }
            servirCaña();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    private void servirCaña() {
        cañasServidas++;
        System.out.println("Se ha servido una caña. Total: " + cañasServidas);
        if (cañasServidas >= nCañas) {
            System.out.println("Barril vacío. Avisando al camarero.");
            barrilVacio.signalAll();
        }
    }

    public void reponer() {
        lock.lock();
        try {
            while (barrilReponiendo || clientesDentro == 0) {
                barrilVacio.await();
            }
            barrilReponiendo = true;
            System.out.print("Camarero reponiendo el barril");
            Thread puntosThread = new Thread(() -> mostrarPuntos());
            puntosThread.start();

            cañasServidas = 0;
            Thread.sleep(2000);
            puntosThread.interrupt();

            System.out.println("\rBarril repuesto.");
            barrilReponiendo = false;
            barrilVacio.signalAll();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    private void mostrarPuntos() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.print(".");
                Thread.sleep(250);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
