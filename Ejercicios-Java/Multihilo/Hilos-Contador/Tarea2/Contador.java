package Procesos.CreacionProcesos.Tarea2;

class Contador {
    private int numero = 1;
    int contador = 0;
    public void incrementar(){
        contador++;
    }
    public int valor(){
        return contador;
    }
//    public synchronized void contar() {
//        while (numero <= 10) {
//            System.out.println(numero);
//            numero++;
//            notifyAll();
//            try {
//                if (numero <= 10) {
//                    wait();
//                }
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//        }
//        notifyAll();
//    }
}

