package EjerciciosAsh;

import java.io.*;
import java.util.ArrayList;

public class Ejercicio1 {

    /*                      CHULETA DE COMO ELEGIR OBJETOS DE ESCRITURA Y LECTURA
    *               PARA TEXTO                  |             PARA BINARIOS
    *    ESCRIBIR     |       LEER    |   DATO  |       ESCRIBIR        |        LEER         |  DATO
    * FileWriter      | FileReader    | Char    |  FileOutputStream     | FileInputStream     |  Bytes
    *                 |               |         |                       | fin-> -1             |
    * BufferedWriter  |BufferedReader | Strings |  BufferedOutputStream | BufferedInputStream |  Bytes
    *                 |               |         |                       | fin-> -1            |
    * PrintWriter     |Scanner        | Tokens  |  DataOutputStream     | DataInputStream     |  int, String, double, etc
    *                 |               |         |                       | fin -> EOFException |
    *                 |               |         |  ObjectOutputStream   |  ObjectInputStream  |  Objetos
    *                 |               |         |                       | fin -> EOFException |
     * */

    public static void main(String[] args) {
        //Pon la ruta a donde tengas tú el archivo paises
        String original = "C:\\Users\\Jesús Garay\\Desktop\\1DAM - Rey\\Programación\\Extra - Funcional\\paises.txt";
        //Si no especificas la ruta se crean en el src
        String formateadoLinea = "paisesConFormato.txt";
        String binario = "paisesMayor600000.bin";

        formatearPaises(original, formateadoLinea);
        ArrayList<Pais> paises = leerPaisesConFormato(formateadoLinea);
        generarFicheroBinario(paises, binario);
    }

    // Parte a
    private static void formatearPaises(String original, String formateado) {
        //Como no necesitas leer caracter a caracter puedes usar BufferedReader
        //Como la escritura es linea a linea se puede usar BufferedWriter
        try (BufferedReader br = new BufferedReader(new FileReader(original));
             BufferedWriter bw = new BufferedWriter(new FileWriter(formateado))) {

            String linea;
            //Sabiendo que la primera linea del archivo es un pais leo las 4 lineas
            //y las guardo en variables. Si al tratar de leer un nuevo bloque de 4 lineas
            //la linea leida devuelve null se ha llegado al final del archivo.
            while ((linea = br.readLine()) != null) {
                String nombre = linea;
                String poblacion = br.readLine();
                String extension = br.readLine();
                String pib = br.readLine();

                //Construyo un string con el formato deseado, lo escribo y añado un salto de linea
                String lineaFormateada = nombre + ";" + poblacion + "*" + extension + ";" + pib;
                bw.write(lineaFormateada);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Parte b
    private static ArrayList<Pais> leerPaisesConFormato(String formateadoLinea) {
        ArrayList<Pais> paises = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(formateadoLinea))) {
            //Lo mismo, quiero ir leyendo linea a linea del archivo formateado
            String linea;
            while ((linea = br.readLine()) != null) {
                //String tiene un metodo split que lo divide cada vez que encuentra lo que le indiques
                //Creo un Array de Strings para guardar las partes.
                //Divido cuando encuentro punto y coma
                String [] partes = linea.split(";");
                //Lo primero que hay antes de la coma es el nombre
                String nombre = partes[0];
                //La poblacion y la extension se separan por "*", no por ";"
                //El asterisco sin mas es un metacaracter que significa otra cosa, si quieres interpretarlo como separador
                //tienes que usar los caracteres de escape \\ como para el salto de linea \n
                //Creo un Array de Strings para las subpartes y divido el segundo bloque del primer Array de Strings cuando se encuentre "*"
                String[] subPartes = partes[1].split("\\*");
                //Guardo cada subparte
                double poblacion = Double.parseDouble(subPartes[0]);
                double extension = Double.parseDouble(subPartes[1]);
                //Guardo la parte tras el ultimo ";"
                double pib = Double.parseDouble(partes[2]);

                if (extension > 500000 && poblacion > 30 && poblacion < 250) {
                    //Si se cumplen las condiciones del enunciado añado al ArrayList el pais
                    paises.add(new Pais(nombre, poblacion, extension, pib));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return paises;
    }

    // Parte c
    private static void generarFicheroBinario(ArrayList<Pais> paises, String binario) {
        //Se me hace raro, pero como tu profe parece que no quiere guardar los objetos, si no solo el texto y los numeros
        //hay que usar DataOutputStream y no ObjectOutputStream. Por si acaso, recuerda que para usar el segundo habría que
        //hacer que pais implementara Serializable
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(binario))) {
            for (Pais pais : paises) {
                if (pais.getExtension() > 600000) {
                    dos.writeUTF(pais.getNombre());
                    dos.writeDouble(pais.getPoblacion());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
