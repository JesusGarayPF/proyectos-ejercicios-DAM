//package Procesos.CreacionProcesos.tarea06;
//
//public class Main {
//    public static void main(String[] args) {
//        Cliente cliente1 = new Cliente("Juan Pérez", "12345678A", null, 0, null);
//        Cliente cliente2 = new Cliente("Ana López", "87654321B", null, 0, null);
//
//        Cuenta cuenta1 = new Cuenta("ES1234567890", 1000, cliente1);
//        Cuenta cuenta2 = new Cuenta("ES0987654321", 500, cliente2);
//
//        Cliente operacionIngreso = new Cliente("Juan Pérez", "12345678A", cuenta1, "ingreso", 200, null);
//        Cliente operacionReintegro = new Cliente("Ana López", "87654321B", cuenta2, "reintegro", 100, null);
//        Cliente operacionTransferencia = new Cliente("Juan Pérez", "12345678A", cuenta1, "transferencia", 300, cuenta2);
//
//        Thread hiloIngreso = new Thread(operacionIngreso);
//        Thread hiloReintegro = new Thread(operacionReintegro);
//        Thread hiloTransferencia = new Thread(operacionTransferencia);
//
//        hiloIngreso.start();
//        hiloReintegro.start();
//        hiloTransferencia.start();
//
//        try {
//            hiloIngreso.join();
//            hiloReintegro.join();
//            hiloTransferencia.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("Saldo final de cuenta1: " + cuenta1.getSaldo());
//        System.out.println("Saldo final de cuenta2: " + cuenta2.getSaldo());
//    }
//}
