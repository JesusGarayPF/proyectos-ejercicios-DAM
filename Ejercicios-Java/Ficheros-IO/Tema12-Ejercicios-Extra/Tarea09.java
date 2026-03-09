package Tema12;

import java.io.*;
import java.nio.file.Path;
import java.util.Scanner;

public class Tarea09 {
    private static final int LIM_INFERIOR = 32;
    private static final int LIM_SUPERIOR = 127;
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Path pruebas = Path.of("C:\\Users\\Jesús Garay\\Desktop\\Pruebas_directorios");
        System.out.println("Introduzca el nombre del fichero que quiere crear");
        String fichero = sc.nextLine();
        Path original = Path.of(pruebas + File.separator + fichero);
        Path cifrado = Path.of(pruebas + File.separator + fichero + "_cifrado");
        Path descifrado = Path.of(pruebas + File.separator + fichero + "_descifrado");
        boolean haTerminado = false;
        while (!haTerminado) {
            System.out.println("""
                    Elija una opcion:
                    1-Mostrar contenido del fichero
                    2-Cifrar fichero original
                    3-Descifrar fichero cifrado
                    4-Escribir fichero original
                    5-Salir
                    """);
            int opcion = Integer.parseInt(sc.nextLine());
            int clave = 0;
            switch (opcion) {
                case 1:
                    System.out.println("""
                            Que fichero quieres mostrar?
                            1-Original
                            2-Cifrado
                            3-Descifrado
                            """);
                    int subopcion = Integer.parseInt(sc.nextLine());
                    switch (subopcion){
                        case 1:
                            if (original.toFile().exists() && original.toFile().isFile()){
                                mostrarContenidoFichero(original);
                            }else {
                                System.out.println("El fichero original no existe");
                            }
                            break;
                        case 2:
                            if (cifrado.toFile().exists() && cifrado.toFile().isFile()){
                                mostrarContenidoFichero(cifrado);
                            }else {
                                System.out.println("El fichero cifrado no existe");
                            }
                            break;
                        case 3:
                            if (descifrado.toFile().exists() && descifrado.toFile().isFile()){
                                mostrarContenidoFichero(descifrado);
                            }else {
                                System.out.println("El fichero original no existe");
                            }
                            break;
                        default:
                            System.out.println("La opcion escogida no existe");
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Introduce una clave de cifrado");
                    clave = Integer.parseInt(sc.nextLine());
                    cifrar(original, cifrado, clave);
                    break;
                case 3:
                    descifrar(cifrado, original, clave);
                    break;
                case 4:
                    escribirFichero(original.toFile());
                    break;
                default:
                    haTerminado = true;
                    break;
            }
        }
    }

    private static void cifrar(Path original, Path cifrado, int clave) {
        if (clave > (LIM_SUPERIOR - LIM_INFERIOR)) {
            throw new IllegalArgumentException(String.format("La clave de cifrado no puede ser mayor que %d", LIM_SUPERIOR - LIM_INFERIOR));
        }
        if (original.toFile().exists() && original.toFile().isFile()) {
            System.out.println("El archivo de origen existe");
            if (cifrado.toFile().exists() && original.toFile().isFile()) {
                try (BufferedReader br = new BufferedReader(new FileReader(original.toFile()))) {
                    try (BufferedWriter wr = new BufferedWriter(new FileWriter(cifrado.toFile()))) {
                        int caracter = br.read();
                        while (caracter != -1) {
                            if (caracter > LIM_SUPERIOR && caracter < LIM_INFERIOR) {
                                wr.write(caracter);
                            } else {
                                int charCifrado = caracter + clave;
                                if (charCifrado > LIM_SUPERIOR) {
                                    charCifrado = LIM_INFERIOR + (charCifrado % LIM_SUPERIOR);
                                }
                                wr.write((char)charCifrado);
                            }
                            caracter = br.read();
                        }
                    }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("EL fichero de cifrado no existe");
            }
        } else {
            System.out.println("El archivo de origen no existe");
        }
    }

    private static void descifrar(Path cifrado, Path original, int clave) {
        if (clave > (LIM_SUPERIOR - LIM_INFERIOR)) {
            throw new IllegalArgumentException(String.format("La clave de cifrado no puede ser mayor que %d", LIM_SUPERIOR - LIM_INFERIOR));
        }
        if (cifrado.toFile().exists() && cifrado.toFile().isFile()) {
            System.out.println("El archivo cifrado existe");
            if (original.toFile().exists() && cifrado.toFile().isFile()) {
                System.out.println("El archivo original existe");
                try (BufferedReader readCifrado = new BufferedReader(new FileReader(cifrado.toFile()))) {
                    try (BufferedReader readOriginal = new BufferedReader(new FileReader(original.toFile()))) {
                        File descifrado = original.getParent().resolve(cifrado.getFileName() + "_descifrado").toFile();
                        try (BufferedWriter wrDescrifrado = new BufferedWriter(new FileWriter(descifrado))) {
                            int charCifrado = readCifrado.read();
                            int charOriginal = readOriginal.read();
                            while (charCifrado != -1) {
                                charOriginal = readOriginal.read();
                                charCifrado = readCifrado.read();
                                if (charCifrado > LIM_SUPERIOR || charCifrado < LIM_INFERIOR) {
                                    if (charCifrado == charOriginal) {
                                        wrDescrifrado.write(charCifrado);
                                    }
                                } else {
                                    int charDescifrado = charCifrado - clave;
                                    if (charDescifrado < LIM_INFERIOR) {
                                        if (charDescifrado == charOriginal) {
                                            charDescifrado = LIM_SUPERIOR + (charDescifrado % LIM_INFERIOR);
                                        }
                                        wrDescrifrado.write(charDescifrado);
                                    }
                                }
                            }
                        }
                    }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            System.out.println("El archivo de cifrado no existe");
        }
    }

    private static void mostrarContenidoFichero(Path fichero) {
        System.out.println("Leyendo fichero");
        try (BufferedReader rd = new BufferedReader(new FileReader(fichero.toFile()))) {
            char caracter = (char) rd.read();
            while (caracter != -1) {
                System.out.print(caracter);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void escribirFichero(File fichero) {
        System.out.println("Escriba el contenido que quiera añadir al fichero");
        boolean terminadoEscribir = false;
        try (BufferedWriter wr = new BufferedWriter(new FileWriter(fichero))) {
            while (!terminadoEscribir) {
                wr.write(sc.nextLine());
                System.out.println("Quiere seguir escribiendo? S para seguir N para finalizar");
                String llave = sc.nextLine().toUpperCase();
                if (llave.equals("N")) {
                    terminadoEscribir = true;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
