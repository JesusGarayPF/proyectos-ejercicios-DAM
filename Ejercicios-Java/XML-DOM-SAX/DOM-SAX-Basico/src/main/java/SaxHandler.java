import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class SaxHandler extends DefaultHandler {
    private String ciudad = "";
    private List<Double> temperaturasMax = new ArrayList<>();
    private List<Double> temperaturasMin = new ArrayList<>();
    private List<Double> humedades = new ArrayList<>();
    private List<Double> presiones = new ArrayList<>();
    private String nodoTexto = "";
    private PrintWriter writer;
    public SaxHandler(PrintWriter writer) {
        this.writer = writer;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        nodoTexto = "";
        switch (qName) {
            case "city":
                ciudad = attributes.getValue("name").toUpperCase();
                break;
            case "temperature":
                double tempMax = Double.parseDouble(attributes.getValue("max"));
                double tempMin = Double.parseDouble(attributes.getValue("min"));
                temperaturasMax.add(tempMax);
                temperaturasMin.add(tempMin);
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        nodoTexto += new String(ch, start, length).trim();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("humidity".equals(qName)){
            double humedad = Double.parseDouble(nodoTexto);
            humedades.add(humedad);
        }
        if ("pressure".equals(qName)){
            double presion = Double.parseDouble(nodoTexto);
            presiones.add(presion);
        }
        if ("city".equals(qName)){
            escribirDatosCiudad();
            temperaturasMax.clear();
            temperaturasMin.clear();
            humedades.clear();
            presiones.clear();
        }
    }

    private void escribirDatosCiudad() {
        double sumaTempMax  = 0, sumaTempMin  = 0, sumaHumedad  = 0, sumaPresiones  = 0;
        double mediaTempMax = 0, mediaTempMin = 0, mediaTempMedia = 0, mediaHumedad = 0, mediaPresiones = 0;
        for (Double temperatura : temperaturasMax){
            sumaTempMax += temperatura;
        }
        for (Double temperatura : temperaturasMin){
            sumaTempMin += temperatura;
        }
        for (Double humedad : humedades){
            sumaHumedad += humedad;
        }
        for (Double presion : presiones){
            sumaPresiones += presion;
        }
        mediaTempMax = sumaTempMax/temperaturasMax.size();
        mediaTempMin = sumaTempMin/temperaturasMin.size();
        mediaTempMedia = (mediaTempMax + mediaTempMin)/2;
        mediaHumedad = sumaHumedad/humedades.size();
        mediaPresiones = sumaPresiones/presiones.size();

        writer.println(ciudad);
        writer.println("-".repeat(63));
        writer.printf("%-10s %-10s %-10s %-15s %-15s\n", "T mínima", "T máxima", "T media", "Humedad media", "Presión media");
        writer.printf("%-10.2f %-10.2f %-10.2f %-15.2f %-15.2f\n\n", mediaTempMin, mediaTempMax, mediaTempMedia, mediaHumedad, mediaPresiones);

    }
}
