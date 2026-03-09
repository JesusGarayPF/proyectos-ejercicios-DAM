package UT01;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.File;

public class SAX {
    public static void main(String[] args) {
        String rutaEjecucion = System.getProperty("user.dir");
        String rutaArchivoEntrada = rutaEjecucion + "\\ProcesamientoXML\\src\\main\\java\\UT01\\weather-data.xml";
        String rutaArchivoSalida = rutaEjecucion + "\\ProcesamientoXML\\src\\main\\java\\UT01\\resumen-clima.txt";
        generarInformeTexto(rutaArchivoEntrada, rutaArchivoSalida);
    }

    public static void generarInformeTexto(String archivoEntrada, String archivoSalida) {
        try (PrintWriter escritor = new PrintWriter(new FileWriter(archivoSalida))) {
            SAXParserFactory fabrica = SAXParserFactory.newInstance();
            SAXParser analizadorSAX = fabrica.newSAXParser();

            analizadorSAX.parse(new File(archivoEntrada), new Handler(escritor));
            System.out.println("Informe de texto generado correctamente en " + archivoSalida);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}