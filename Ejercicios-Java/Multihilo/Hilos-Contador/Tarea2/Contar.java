package Procesos.CreacionProcesos.Tarea2;

public class Contar {
    public static void main(String[] args) {
        Contador contador = new Contador();
        for (int i = 0; i < 10; i++) {
            new HiloCuenta(contador).start();
        }
    }
}

