package Procesos.CreacionProcesos.Tarea05;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class HilosCalculador implements Runnable {
    private boolean suspendido;
    private final Lock lock;
    private final Condition condition;

    public HilosCalculador() {
        this.suspendido = false;
        this.lock = new ReentrantLock();
        this.condition = lock.newCondition();
    }

    @Override
    public void run() {
        boolean terminar = false;
        Calculadora calculadora = new Calculadora();

        while (!terminar) {
            lock.lock();
            try {
                while (suspendido) {
                    condition.await();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
            terminar = calculadora.calcularParte();
        }
    }

    public void suspender() {
        lock.lock();
        try {
            suspendido = true;
        } finally {
            lock.unlock();
        }
    }

    public void reanudar() {
        lock.lock();
        try {
            if (suspendido) {
                suspendido = false;
                condition.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    public boolean isSuspended() {
        lock.lock();
        try {
            return suspendido;
        } finally {
            lock.unlock();
        }
    }
}
