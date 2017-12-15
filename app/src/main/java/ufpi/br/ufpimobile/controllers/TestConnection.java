package ufpi.br.ufpimobile.controllers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Classe que testa se a conexão do usuário com a internet está ativada (Wifi e Dados Móveis).
 * Created by zenote on 29/05/2015.
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