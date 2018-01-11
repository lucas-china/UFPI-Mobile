package ufpi.br.ufpimobile.Maps;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;

import java.util.List;

import ufpi.br.ufpimobile.R;
import ufpi.br.ufpimobile.model.GeoPointsDatabase;
import ufpi.br.ufpimobile.model.Node;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOnibus extends SupportMapFragment implements OnMapReadyCallback {

    //Localização da UFPI
    private static LatLng ufpiLocation = null;
    private MapView mapView;
    private GoogleMap mMap;
    private int tipoDeMapa = 3; //Mapa Normal
    private Polyline polyline;
    private String type = "11"; //Tipo do marcador
    private static final String TAG = "MapsActivity";


    public void onCreate(Bundle savedInstanceStatee) {
        ufpiLocation = new LatLng(-5.0565465, -42.7946826);
        //mudarTipoDeMapa(3);
        super.onCreate(savedInstanceStatee);
        getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     * Metodo que implementa as configurações do mapa
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);

        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        addMarkerByType(type); //Adiciona marcadores ao mapa

        try {
            mMap.setMyLocationEnabled(true);
        }catch (SecurityException e){
            Log.e(TAG, "Error",e);
        }
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(ufpiLocation)      // Sets the center of the map to UFPI
                .zoom(15)                   // Sets the zoom
                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


        // Add a marker in Sydney and move the camera
        // LatLng sydney = new LatLng(-34, 151);
        // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    //Adiciona marcadores ao mapa
    public void addMarkerByType(String type) {
        if (type != null) {

            GeoPointsDatabase m = new GeoPointsDatabase(getContext());
            //Pesquisa os pontos no banco de dados por tipo
            List<Node> nodes = m.selectByType(type);
            if (nodes.size() > 0) {
                for (Node n : nodes) {
                    //Adiciona o marcador de cada ponto ao mapa
                    LatLng latLng = new LatLng(n.getLocalization().latitude, n.getLocalization().longitude);
                    customAddMarker(latLng, n.getName(), n.getDescription());
                }
            }
        }
    }

    public void setType(String type) {
        this.type = type;
    }

    //Mexer no tipo de marcador presente no mapa
    public void customAddMarker(LatLng latLng, String title, String snippet) {
        MarkerOptions options = new MarkerOptions();
        options.position(latLng).title(title).snippet(snippet).draggable(true);
        Marker marker = mMap.addMarker(options);
        marker.showInfoWindow();
    }

    //Metodo autoexplicativo
    public void mudarTipoDeMapa(int mudar) {
        switch (mudar) {
            case 1:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case 2:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case 3:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
        }
    }
}

