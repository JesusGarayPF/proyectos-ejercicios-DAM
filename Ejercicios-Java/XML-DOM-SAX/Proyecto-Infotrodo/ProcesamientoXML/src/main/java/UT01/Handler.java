package UT01;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Handler extends DefaultHandler {
    private PrintWriter escritor;
    private String nombreCiudad;
    private List<Double> temperaturasMaximas = new ArrayList<>();
    private List<Double> temperaturasMinimas = new ArrayList<>();
    private List<Double> humedades = new ArrayList<>();
    private List<Double> presiones = new ArrayList<>();
    private boolean esTemperatura, esHumedad, esPresion;

    public Handler(PrintWriter escritor) {
        this.escritor = escritor;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atributos) throws SAXException {
        if (qName.equalsIgnoreCase("city")) {
            nombreCiudad = atributos.getValue("name");
        } else if (qName.equalsIgnoreCase("temperature")) {
            esTemperatura = true;
            temperaturasMaximas.add(Double.parseDouble(atributos.getValue("max")));
            temperaturasMinimas.add(Double.parseDouble(atributos.getValue("min")));
        } else if (qName.equalsIgnoreCase("humidity")) {
            esHumedad = true;
        } else if (qName.equalsIgnoreCase("pressure")) {
            esPresion = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String contenido = new String(ch, start, length).trim();
        if (esHumedad) {
            humedades.add(Double.parseDouble(contenido));
            esHumedad = false;
        } else if (esPresion) {
            presiones.add(Double.parseDouble(contenido));
            esPresion = false;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("city")) {
            generarInformeCiudad();
            temperaturasMaximas.clear();
            temperaturasMinimas.clear();
            humedades.clear();
            presiones.clear();
        }
    }

    private void generarInformeCiudad() {
        double promedioMax = temperaturasMaximas.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        double promedioMin = temperaturasMinimas.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        double promedioTemp = (promedioMax + promedioMin) / 2;
        double promedioHumedad = humedades.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        double promedioPresion = presiones.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

        escritor.println("Ciudad: " + nombreCiudad);
        escritor.println("--------------------------------------------------");
        escritor.printf("T mínima: %.2f | T máxima: %.2f | T media: %.2f\n", promedioMin, promedioMax, promedioTemp);
        escritor.printf("Humedad media: %.2f | Presión media: %.2f\n", promedioHumedad, promedioPresion);
        escritor.println();
    }
}