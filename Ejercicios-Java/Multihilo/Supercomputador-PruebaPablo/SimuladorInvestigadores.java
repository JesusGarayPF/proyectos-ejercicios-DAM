package Procesos.CreacionProcesos.pruebaPablo;

public class SimuladorInvestigadores {

    public static void main(String[] args) {
        final int maxUsuarios = 5;
        final int nucleos = 16;
        final int ram = 32;
        final int totalUsuarios = 10;


        Supercomputador supercomputador = new Supercomputador(maxUsuarios, nucleos, ram);

        Thread[] usuarios = new Thread[totalUsuarios];

        for (int i = 0; i < totalUsuarios; i++) {
            usuarios[i] = new Thread(new Usuario(i + 1, supercomputador));
            usuarios[i].start();
        }

        for (Thread usuario : usuarios) {
            try {
                usuario.join();
            } catch (InterruptedException e) {
                System.out.println("Error esperando a un usuario: " + e.getMessage());
            }
        }
        System.out.print("""
                          SIMULACIÓN COMPLETADA
                        - - - - - - - - - - - - - -
                        """);
    }
}