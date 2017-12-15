package ufpi.br.ufpimobile.model;

import android.app.Application;

import ufpi.br.ufpimobile.views.MapFragment;

/**
 * Classe utilizada para manter a mesma referência de um mapa em toda a aplicação.
 * Created by zenote on 29/05/2015.
 */
public class ApplicationObject extends Application {
    public MapFragment mapa = new MapFragment();

    public void setMap(MapFragment map){
        this.mapa = map;
    }
}
