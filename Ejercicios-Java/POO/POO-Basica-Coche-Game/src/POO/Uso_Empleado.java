package POO;

import java.util.Date;
import java.util.GregorianCalendar;

public class Uso_Empleado {
    public static void main(String[] args) {

        /*Empleado empleado1 = new Empleado("Paco Gomez", 8500, 1990, 11, 17);
        Empleado empleado2 = new Empleado("Ana Lopez", 95000, 1995, 06, 02);
        Empleado empleado3 = new Empleado("María Martín", 105000, 2002, 03, 15);
        empleado1.subeSueldo(5);
        empleado2.subeSueldo(5);
        empleado3.subeSueldo(5);
        System.out.println("Nombre: " + empleado1.dameNombre() + " Sueldo: " + empleado1.dameSueldo() + "Fecha de Alta: " + empleado1.dameFechaContrato());
        System.out.println("Nombre: " + empleado2.dameNombre() + " Sueldo: " + empleado2.dameSueldo() + "Fecha de Alta: " + empleado2.dameFechaContrato());
        System.out.println("Nombre: " + empleado3.dameNombre() + " Sueldo: " + empleado3.dameSueldo() + "Fecha de Alta: " + empleado3.dameFechaContrato());*/

        Empleado[] misEmpleados = new Empleado[3];
        misEmpleados[0] = new Empleado("Paco Gomez", 85000, 1990, 12, 17);
        misEmpleados[1] = new Empleado("Ana Lopez", 95000, 1995, 06, 02);
        misEmpleados[2] = new Empleado("María Martín", 105000, 2002, 03, 15);
        for (int i = 0; i < 3; i++) {
            misEmpleados[i].subeSueldo(5);
        }
        /*for(Empleado e: misEmpleados){ //BUCLE FOR MEJORADO
            e.subeSueldo(5);
        }*/

        for (int i = 0; i < 3; i++) {
            System.out.println("Nombre: " + misEmpleados[i].dameNombre() + " Sueldo: " + misEmpleados[i].dameSueldo() + " Fecha de alta: " + misEmpleados[i].dameFechaContrato());
        }
        /*for(Empleado e: misEmpleados){
            System.out.println("Nombre: " + e.dameNombre() + " Sueldo: " + e.dameSueldo() + " Fecha de alta: " + e.dameFechaContrato());
        }*/
    }
}

class Empleado {
    public Empleado(String nom, double sue, int agno, int mes, int dia) {
        nombre = nom;
        sueldo = sue;
        GregorianCalendar Calendario = new GregorianCalendar(agno, mes - 1, dia);
        altaContrato = Calendario.getTime();
    }

    public String dameNombre() { //GETTER
        return nombre;
    }

    public double dameSueldo() {
        return sueldo;
    }

    public Date dameFechaContrato() {
        return altaContrato;
    }

    public void subeSueldo(double porcentaje) {
        double aumento = sueldo * porcentaje / 100;
        sueldo += aumento;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "sueldo=" + sueldo +
                '}';
    }

    private String nombre;
    private double sueldo;
    private Date altaContrato;
}