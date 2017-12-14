package ufpi.br.ufpimobile.views;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import ufpi.br.ufpimobile.R;
import ufpi.br.ufpimobile.interfaces.InterfaceGetListOfGeopoints;
import ufpi.br.ufpimobile.model.Node;

import java.util.List;


/**
 * Created by HugoPiauilino on 30/04/15.
 */
public class MapFragment extends android.support.v4.app.Fragment implements OnMapReadyCallback, InterfaceGetListOfGeopoints {

    private static final LatLng ufpiLocation = new LatLng(-5.057772, -42.797009);
    private MapView mapView;
    private GoogleMap googleMap;
    private int tipoDeMapa = 2; //Mapa Normal
    private Polyline polyline;
    private String type = null; //Tipo do marcador


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        mapView = (MapView) view.findViewById(R.id.map);

        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);

        MapsInitializer.initialize(getActivity());

        if (mapView != null) {
            googleMap = mapView.getMap();

            googleMap.getUiSettings().setMyLocationButtonEnabled(true);

            googleMap.setMyLocationEnabled(true);

            googleMap.getUiSettings().setZoomControlsEnabled(true);

        }

        mudarTipoDeMapa();

        return view;

    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    /**
     * Seta uma ancora no ponto central da UFPI
     *
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        //googleMap.addMarker(new MarkerOptions().position(ufpiLocation).title("Universidade Federal do Piauí (UFPI)"));
        // Construct a CameraPosition focusing on Mountain View and animate the camera to that position.
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(ufpiLocation)      // Sets the center of the map to UFPI
                .zoom(15)                   // Sets the zoom
                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        addMarkerByType(type); //Adiciona marcadores ao mapa
    }

    public void customAddMarker(LatLng latLng, String title, String snippet) {
        MarkerOptions options = new MarkerOptions();
        options.position(latLng).title(title).snippet(snippet).draggable(true);
        Marker marker = googleMap.addMarker(options);
        marker.showInfoWindow();
    }

    public void addMarkerByType(String type){
        if(type != null) {
            List<Node> nodes = ((Mapas) getActivity()).getGeoPointsDatabase().selectByType(type);
            if (nodes.size() > 0) {
                for (Node n : nodes) {
                    LatLng latLng = new LatLng(n.getLocalization().latitude, n.getLocalization().longitude);
                    customAddMarker(latLng, n.getName(), n.getDescription());
                }
            }
        }
    }


    public void devolveListaDeGeoPoints(List<LatLng> geopointList, Node originNode, Node destinationNode) {
        drawRoute(geopointList, originNode, destinationNode);

    }


    public void drawRoute(List<LatLng> list, Node origem, Node destino) {
        PolylineOptions polylineOptions = null;

        if (polylineOptions == null) {
            polylineOptions = new PolylineOptions();
            LatLng origemLatLng = new LatLng(origem.getLocalization().latitude, origem.getLocalization().longitude);
            customAddMarker(origemLatLng, "Inicio", origem.getName());
            for (int i = 0; i < list.size(); i++) {
                polylineOptions.add(list.get(i));
            }
            LatLng destinoLatLng = new LatLng(destino.getLocalization().latitude, destino.getLocalization().longitude);
            customAddMarker(destinoLatLng, "Fim", destino.getName());
            polylineOptions.color(Color.rgb(0,135,193)).width(8);

            polyline = googleMap.addPolyline(polylineOptions);
        } else {
            polyline.setPoints(list);
        }
    }

    public void setTipoDeMapa(int tipoDeMapa) {
        this.tipoDeMapa = tipoDeMapa;
    }

    public int getTipoDeMapa() {
        return tipoDeMapa;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void mudarTipoDeMapa() {

        //Log.i("TipoMapa", "Entrou no mudarTipoDeMapa");
        //Log.i("TipoMapa", "tipoDeMapa = " + tipoDeMapa);
        switch (tipoDeMapa) {
            case 2:
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case 4:
                googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case 3:
                googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
        }
    }
}