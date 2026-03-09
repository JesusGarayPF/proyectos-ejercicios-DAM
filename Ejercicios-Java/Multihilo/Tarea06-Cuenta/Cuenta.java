//package Procesos.CreacionProcesos.tarea06;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;
//
//@Getter
//@Setter
//public class Cuenta {
//    private final String numCuenta;
//    private int saldo;
//    private final Cliente cliente;
//    private final Lock lock = new ReentrantLock();
//
//    public Cuenta(String numCuenta, int saldoInicial, Cliente cliente) {
//        this.numCuenta = numCuenta;
//        this.saldo = saldoInicial;
//        this.cliente = cliente;
//    }
//
//    public void ingreso(int cuanto) {
//        lock.lock();
//        try {
//            if (cuanto > 0) {
//                saldo += cuanto;
//                System.out.println("Ingreso de " + cuanto + " realizado. Nuevo saldo: " + saldo);
//            }
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    public void reintegro(int cuanto) throws SaldoInsuficienteException {
//        lock.lock();
//        try {
//            if (cuanto > saldo) {
//                throw new SaldoInsuficienteException("Saldo insuficiente para el reintegro de " + cuanto);
//            } else if (cuanto > 0) {
//                saldo -= cuanto;
//                System.out.println("Reintegro de " + cuanto + " realizado. Nuevo saldo: " + saldo);
//            }
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    public void transferencia(int cuanto, Cuenta destino) throws SaldoInsuficienteException {
//        Cuenta primeraCuenta;
//        Cuenta segundaCuenta;
//
//        if (this.hashCode() < destino.hashCode()) {
//            primeraCuenta = this;
//            segundaCuenta = destino;
//        } else {
//            primeraCuenta = destino;
//            segundaCuenta = this;
//        }
//
//        primeraCuenta.lock.lock();
//        segundaCuenta.lock.lock();
//        try {
//            if (cuanto > saldo) {
//                throw new SaldoInsuficienteException("Saldo insuficiente para transferencia de " + cuanto);
//            } else if (cuanto > 0) {
//                this.saldo -= cuanto;
//                destino.ingreso(cuanto);
//                System.out.println("Transferencia de " + cuanto + " realizada de " + this.numCuenta + " a " + destino.numCuenta);
//            }
//        } finally {
//            segundaCuenta.lock.unlock();
//            primeraCuenta.lock.unlock();
//        }
//    }
//}
