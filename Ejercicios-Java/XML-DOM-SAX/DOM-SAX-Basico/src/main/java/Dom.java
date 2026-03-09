import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Dom {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException, TransformerException {
        System.out.println("Dime la ubicación del fichero a procesar");
        String ruta = sc.nextLine();
        Path rutaPath = Path.of(ruta);
        if (!rutaPath.toFile().exists()) {
            System.out.println("El fichero no existe");
            return;
        }

        System.out.println("Dime la ubicación del fichero XML de salida");
        String rutaSalida = sc.nextLine();
        Path rutaPathSalida = Path.of(rutaSalida);
        if (rutaPathSalida.toFile().exists() && !rutaPathSalida.toFile().isDirectory()) {
            System.out.println("¿Quieres sobrescribirlo?");
            String respuesta = sc.nextLine();
            if (respuesta.equalsIgnoreCase("no")) {
                return;
            }
        }else if(rutaPathSalida.toFile().isDirectory()){
            System.out.println("No se puede crear en esta ruta puesto que es un directorio");
            return;
        }

        DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dBF.newDocumentBuilder();
        Document doc = builder.parse(rutaPath.toFile());

        // Crear el documento de salida
        Document outputDoc = builder.newDocument();
        Element rootElement = outputDoc.createElement("city-weather-summary");
        outputDoc.appendChild(rootElement);

        XPath xPath = XPathFactory.newInstance().newXPath();
        String ciudadExpresion = "/weather-data/city";
        XPathExpression ciudadExpresionXPath = xPath.compile(ciudadExpresion);
        NodeList ciudades = (NodeList) ciudadExpresionXPath.evaluate(doc, XPathConstants.NODESET);
        String temperaturaMaxExpresion = "station/measurements/period/temperature/@max";
        String temperaturaMinExpresion = "station/measurements/period/temperature/@min";
        String humedadMediaExpresion = "station/measurements/period/humidity";
        String presionExpresion = "station/measurements/period/pressure";

        XPathExpression temperaturaMaxXPath = xPath.compile(temperaturaMaxExpresion);
        XPathExpression temperaturaMinXPath = xPath.compile(temperaturaMinExpresion);
        XPathExpression humedadMediaXPath = xPath.compile(humedadMediaExpresion);
        XPathExpression presionXPath = xPath.compile(presionExpresion);

        for (int i = 0; i < ciudades.getLength(); i++) {
            Element ciudad = (Element) ciudades.item(i);
            String nombreCiudad = ciudad.getAttribute("name");



            NodeList temperaturasMaximas = (NodeList) temperaturaMaxXPath.evaluate(ciudad, XPathConstants.NODESET);
            NodeList temperaturasMinimas = (NodeList) temperaturaMinXPath.evaluate(ciudad, XPathConstants.NODESET);
            NodeList humedadesMedias = (NodeList) humedadMediaXPath.evaluate(ciudad, XPathConstants.NODESET);
            NodeList presiones = (NodeList) presionXPath.evaluate(ciudad, XPathConstants.NODESET);

            int valorMax = Integer.MIN_VALUE;
            int valorMin = Integer.MAX_VALUE;
            double sumaTemperaturas = 0;
            int contadorTemperatura = 0;
            double sumaHumedades = 0;
            double sumaPresion = 0;


            for (int j = 0; j < temperaturasMaximas.getLength(); j++) {
                int tempMax = Integer.parseInt(temperaturasMaximas.item(j).getTextContent());
                int tempMin = Integer.parseInt(temperaturasMinimas.item(j).getTextContent());
                int humedad = Integer.parseInt(humedadesMedias.item(j).getTextContent());
                int presion = Integer.parseInt(presiones.item(j).getTextContent());

                valorMax = Math.max(valorMax, tempMax);
                valorMin = Math.min(valorMin, tempMin);
                sumaTemperaturas += tempMax + tempMin;
                contadorTemperatura += 2;
                sumaHumedades += humedad;
                sumaPresion += presion;
            }

            double mediaTemperatura = sumaTemperaturas / contadorTemperatura;
            double mediaHumedad = sumaHumedades / humedadesMedias.getLength();
            double mediaPresion = sumaPresion / presiones.getLength();

            Element cityElement = outputDoc.createElement("city");
            cityElement.setAttribute("id", ciudad.getAttribute("id"));
            cityElement.setAttribute("name", nombreCiudad);
            rootElement.appendChild(cityElement);

            Element avgTempElement = outputDoc.createElement("average-temperature");
            cityElement.appendChild(avgTempElement);

            Element maxTempElement = outputDoc.createElement("max");
            maxTempElement.setTextContent(String.format("%.2f", (double) valorMax));
            avgTempElement.appendChild(maxTempElement);

            Element minTempElement = outputDoc.createElement("min");
            minTempElement.setTextContent(String.format("%.2f", (double) valorMin));
            avgTempElement.appendChild(minTempElement);

            Element avgElement = outputDoc.createElement("avg");
            avgElement.setTextContent(String.format("%.2f", mediaTemperatura));
            avgTempElement.appendChild(avgElement);

            Element avgHumidityElement = outputDoc.createElement("average-humidity");
            avgHumidityElement.setTextContent(String.format("%.2f", mediaHumedad));
            cityElement.appendChild(avgHumidityElement);

            Element avgPressureElement = outputDoc.createElement("average-pressure");
            avgPressureElement.setTextContent(String.format("%.2f", mediaPresion));
            cityElement.appendChild(avgPressureElement);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(outputDoc);
        StreamResult result = new StreamResult(new File(rutaSalida));
        transformer.transform(source, result);

        System.out.println("El archivo XML de salida ha sido creado exitosamente en: " + rutaSalida);
    }
}
