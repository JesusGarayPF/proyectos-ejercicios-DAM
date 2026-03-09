import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ProcesadorSAX {
     static Scanner sc = new Scanner(System.in);
//    String rutaEntrada = "src\\main\\java\\weather-data.xml";
//    String rutaSalida = "src\\main\\java\\informe-sax.txt";

    public static void main(String[] args) {
        System.out.println("Introduce la ruta al archivo que quieres procesar");
        String rutaEntrada = sc.nextLine();
        File archivoEntrada = new File(rutaEntrada);
        if (!archivoEntrada.exists()){
            System.out.println("El archivo no existe. Terminando el programa");
            return;
        }
        System.out.println("Introduce la ruta de salida del archivo");
        String rutaSalida = sc.nextLine();
        File archivoSalida = new File(rutaSalida);
        if (archivoSalida.exists()){
            System.out.println("El archivo existe. Quieres sobrescribirlo? 1-Si 2-No");
            int respuesta = Integer.parseInt(sc.nextLine());
            if (respuesta == 2 ){
                System.out.println("Terminando sin sobreescribir");
                return;
            }
        }
        generarXML(rutaEntrada, rutaSalida);
    }
    public static void generarXML(String rutaEntrada, String rutaSalida) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaSalida))) {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            SaxHandler handler = new SaxHandler(writer);
            parser.parse(new File(rutaEntrada),handler);
            System.out.println("El informe se ha generado correctamente");

        } catch (IOException | ParserConfigurationException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
}

