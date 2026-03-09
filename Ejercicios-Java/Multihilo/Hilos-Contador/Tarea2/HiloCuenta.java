package Procesos.CreacionProcesos.Tarea2;

class HiloCuenta extends Thread {
    private Contador contador;
    public HiloCuenta(Contador contador) {
        this.contador = contador;
    }
    @Override
    public void run() {
        //contador.contar();
        contador.incrementar();
        System.out.println(contador);
    }
}

