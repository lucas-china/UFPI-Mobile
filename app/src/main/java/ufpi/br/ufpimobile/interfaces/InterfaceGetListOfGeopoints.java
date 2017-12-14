package ufpi.br.ufpimobile.interfaces;

import com.google.android.gms.maps.model.LatLng;
import ufpi.br.ufpimobile.model.Node;

import java.util.List;

/**
 * Interface criada para devolver uma lista de geopoints para a classe que a implementa.
 * Created by zenote on 29/05/2015.
 */
public interface InterfaceGetListOfGeopoints {
    /**
     * Método que permite o acesso à lista de geopoints
     * @param geopointList Lista de geopoints
     * @param originNode Nó de Origem
     * @param destinationNode Nó de Destino
     */
    void devolveListaDeGeoPoints(List<LatLng> geopointList, Node originNode, Node destinationNode);
}