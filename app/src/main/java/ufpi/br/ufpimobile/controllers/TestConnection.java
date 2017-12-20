package ufpi.br.ufpimobile.controllers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by atendimento on 18/12/17.
 */

public class TestConnection {
    private Context context;

    /**
     * Construtor da classe TestConnection
     *
     * @param context
     */
    public TestConnection(Context context) {
        this.context = context;
    }

    /**
     * Metodo que retorna o estado da conexão
     *
     * @return Conectado - True | Desconectado - False
     */
    public boolean isConnected() {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity != null) {
            NetworkInfo netInfo = connectivity.getActiveNetworkInfo();

            if (netInfo == null) {
                return false;
            }

            int netType = netInfo.getType();

            if (netType == ConnectivityManager.TYPE_MOBILE || netType == ConnectivityManager.TYPE_WIFI) {
                return netInfo.isConnected();
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
