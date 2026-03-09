package dm2e.jesus.viabicing;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    protected static final String WEB_SERVICE_URL =
            "http://wservice.viabicing.cat/v1/getstations.php?v=1";
    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(
                ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkMonitor, filter);
    }
    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(networkMonitor);
        onNetworkOff();
    }
    protected void onNetworkOn() {
        if (_asyncTask == null) {
            _asyncTask = new ObtenerBicis();
            _asyncTask.execute(WEB_SERVICE_URL);
            ((TextView)findViewById(R.id.estadoRed)).setText(R.string.hayRed);

        }
    }
    protected void onNetworkOff() {
        if (_asyncTask != null) {
            _asyncTask.cancel(true);
            _asyncTask = null;
            ((TextView)findViewById(R.id.estadoRed)).setText(R.string.noHayRed);
        }
    }
    public ObtenerBicis _asyncTask;
    BroadcastReceiver networkMonitor = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager cm = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo predefinida = cm.getActiveNetworkInfo();
            if ((predefinida != null) && predefinida.isConnected())
// Hay conexión.
                onNetworkOn();
            else
                onNetworkOff();
        }
    }; // networkMonitor
}