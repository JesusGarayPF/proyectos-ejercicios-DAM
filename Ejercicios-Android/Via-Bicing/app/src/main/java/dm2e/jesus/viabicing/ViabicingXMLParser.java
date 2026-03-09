package dm2e.jesus.viabicing;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

public class ViabicingXMLParser {
    private static final String ns = null;
    public static ViabicingInfo parse(InputStream is)
            throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(
                    XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(is, null); // Codificación predefinida.
            parser.nextTag(); // Nos saltamos el "inicio"
            return readStations(parser);
        } finally {
            is.close();
        }
    } // parse
    //------------------------------------------------------------
    private static ViabicingInfo readStations(XmlPullParser parser)
            throws XmlPullParserException, IOException {
        ViabicingInfo result = new ViabicingInfo();
        parser.require(XmlPullParser.START_TAG, ns, "bicing_stations"); //cambiar por records
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG)
                continue;
            String name = parser.getName();
            switch(name) {
                case "station": //cambiar por record
                    result.addStation(readStation(parser));
                    break;
                case "updatetime": //no existe
                    if (result.timestamp != -1) {
// ¡¡Ya nos habíamos encontrado uno!!
// El XML está roto.
                        throw new XmlPullParserException("Two timestamps");
                    }
                    result.timestamp =
                            Integer.parseInt(readString(parser));
                    break;
                default:
// ¿¿??
                    skip(parser);
            }
        }
        return result;
    } // readStations
    //-----------------------------------------------------------
    private static Station readStation(XmlPullParser parser)
            throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "record");
        Station result = new Station();
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            switch(name) {
                case "ID": //case “ID”
                    result.id = Integer.parseInt(readString(parser));
                    break;
                case "NOM_VIA": //NOM_VIA
                    result.street = readString(parser);
                    break;
                case "NUM_VIA": //NUM_VIA
                    result.streetNumber = readString(parser);
                    break;
                case "slots": //UN VALOR NUMERICO
                    result.slots = readString(parser);
                    break;
                case "bikes": //UN VALOR NUMERICO
                    result.bikes = readString(parser);
                    break;
                default:
                    skip(parser);
            } // switch
        } // while
        return result;
    } // readStation
    //--------------------------------------------------------
    private static String readString(XmlPullParser parser)
            throws XmlPullParserException, IOException {
        String result;
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
            return result;
        }
        else
            return "";
    } // readString
    //---------------------------------------------------------
    private static void skip(XmlPullParser parser)
            throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG)
            throw new IllegalStateException();
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    } // skip
} // ViabicingXMLParser
