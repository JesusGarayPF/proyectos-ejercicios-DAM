package Procesos.CreacionProcesos.tema03;

public class Main {
    public static void main(String[] args) {
        Bar bar = new Bar(5, 10);
        Camarero camarero = new Camarero(bar);
        new Thread(camarero).start();

        for (int i = 0; i < 10; i++) {
            Cliente cliente = new Cliente(bar, (int) (Math.random() * 5) + 1);
            new Thread(cliente).start();
        }
    }
}

