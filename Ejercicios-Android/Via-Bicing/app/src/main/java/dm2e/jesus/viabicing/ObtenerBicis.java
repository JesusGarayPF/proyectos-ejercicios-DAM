package dm2e.jesus.viabicing;

import static android.content.ContentValues.TAG;

import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ObtenerBicis extends AsyncTask<String, Void, Void> {
    public ObtenerBicis _asyncTask;
    protected final String TAG = getClass().getSimpleName();
    @Override
    protected ViabicingInfo doInBackground(String... params) {
        ViabicingInfo viabicingInfo;
        try {
            InputStream is = downloadUrl(params[0]);
            viabicingInfo = ViabicingXMLParser.parse(is);
            android.util.Log.i(TAG, "Ultima actualizacion: " +
                    viabicingInfo.timestamp);
            for (Station st: viabicingInfo.stations) {
                android.util.Log.i(TAG, st.toString());
            }
        } catch (IOException e) {
            android.util.Log.i(TAG, e.toString());
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            android.util.Log.i(TAG, e.toString());
            e.printStackTrace();
        }
        return viabicingInfo;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
    }

    private InputStream downloadUrl(String urlString)
            throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection httpCon;
        httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setRequestMethod("GET");
        httpCon.setDoInput(true);
        httpCon.connect();
        return httpCon.getInputStream();
    }

} // ObtenerBicis


