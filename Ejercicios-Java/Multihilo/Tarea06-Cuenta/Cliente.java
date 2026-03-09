//package Procesos.CreacionProcesos.tarea06;
//
//public class Cliente implements Runnable {
//    private String nombre;
//    private String dni;
//    private Cuenta cuenta;
//    private String operacion;
//    private double cantidad;
//
//    // Constructor general para la clase Cliente
//    public Cliente(String nombre, String dni, Cuenta cuenta, String operacion, double cantidad) {
//        this.nombre = nombre;
//        this.dni = dni;
//        this.cuenta = cuenta;
//        this.operacion = operacion;
//        this.cantidad = cantidad;
//    }
//
//    @Override
//    public void run() {
//        switch (operacion) {
//            case "ingreso":
//                realizarIngreso();
//                break;
//            case "reintegro":
//                realizarReintegro();
//                break;
//            case "transferencia":
//                // Debemos obtener la cuenta de destino en el momento de la transferencia
//                realizarTransferencia();
//                break;
//            default:
//                System.out.println("Operación no válida");
//        }
//    }
//
//    private synchronized void realizarIngreso() {
//        if (cuenta != null) {
//            cuenta.setSaldo(cuenta.getSaldo() + cantidad);
//            System.out.println(nombre + " ha realizado un ingreso de " + cantidad + " en la cuenta " + cuenta.getNumeroCuenta());
//        }
//    }
//
//    private synchronized void realizarReintegro() {
//        if (cuenta != null && cuenta.getSaldo() >= cantidad) {
//            cuenta.setSaldo((int) (cuenta.getSaldo() - cantidad));
//            System.out.println(nombre + " ha realizado un reintegro de " + cantidad + " de la cuenta " + cuenta.getNumeroCuenta());
//        } else {
//            System.out.println(nombre + " no tiene suficiente saldo para reintegrar " + cantidad);
//        }
//    }
//
//    private synchronized void realizarTransferencia() {
//        // La cuenta de destino se obtiene en el contexto donde se llama a esta operación
//        // Por lo tanto, necesitamos que esta función reciba como argumento la cuenta de destino
//        Cuenta cuentaDestino = null; // Este valor será establecido externamente al llamar a este método
//
//        // Aquí debes definir cómo se obtiene la cuenta de destino. Por simplicidad,
//        // vamos a asumir que se pasa como argumento al método. Deberías tener
//        // un mecanismo para definir cuál es la cuenta de destino.
//
//        // Lógica para la transferencia
//        if (cuenta != null && cuentaDestino != null && cuenta.getSaldo() >= cantidad) {
//            cuenta.setSaldo(cuenta.getSaldo() - cantidad);
//            cuentaDestino.setSaldo(cuentaDestino.getSaldo() + cantidad);
//            System.out.println(nombre + " ha transferido " + cantidad + " de la cuenta " + cuenta.getNumeroCuenta() + " a la cuenta " + cuentaDestino.getNumeroCuenta());
//        } else {
//            System.out.println(nombre + " no tiene suficiente saldo para transferir " + cantidad);
//        }
//    }
//
//    // Getters y Setters
//    public String getNombre() {
//        return nombre;
//    }
//
//    public String getDni() {
//        return dni;
//    }
//
//    public Cuenta getCuenta() {
//        return cuenta;
//    }
//
//    public String getOperacion() {
//        return operacion;
//    }
//
//    public double getCantidad() {
//        return cantidad;
//    }
//
//    // Método para establecer la cuenta de destino en caso de transferencia
//    public void setCuentaDestino(Cuenta cuentaDestino) {
//        // Esta función puede ser eliminada si no se quiere que el Cliente tenga un setter para cuenta de destino
//    }
//}
