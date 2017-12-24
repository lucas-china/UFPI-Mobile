package ufpi.br.ufpimobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

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

import ufpi.br.ufpimobile.model.GeoPointsDatabase;
import ufpi.br.ufpimobile.model.Node;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    //Localização da UFPI
    private static final LatLng ufpiLocation = new LatLng(-5.057772, -42.797009);
    private MapView mapView;
    private GoogleMap mMap;
    private int tipoDeMapa = 2; //Mapa Normal
    private Polyline polyline;
    private String type = null; //Tipo do marcador

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_Mapa);
        toolbar.setTitle("Mapa UFPI");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(getApplicationContext(), TelaHome.class);
                finish();
                startActivity(home);
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        //Tipo referente aos Departamentos
        type = "2";
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
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(ufpiLocation)      // Sets the center of the map to UFPI
                .zoom(15)                   // Sets the zoom
                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        /*
        *      Não mexer

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            System.out.println("Não tem permissão");
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        */

        //Setar tipo de mapa como vista por satelite
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        //mudarTipoDeMapa();

        addMarkerByType(type); //Adiciona marcadores ao mapa

        // Add a marker in Sydney and move the camera
        // LatLng sydney = new LatLng(-34, 151);
        // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    //Adiciona marcadores ao mapa
    public void addMarkerByType(String type) {
        if (type != null) {

            GeoPointsDatabase m = new GeoPointsDatabase(this);
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
    public void mudarTipoDeMapa() {

        //Log.i("TipoMapa", "Entrou no mudarTipoDeMapa");
        //Log.i("TipoMapa", "tipoDeMapa = " + tipoDeMapa);
        switch (tipoDeMapa) {
            case 2:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case 4:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case 3:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
        }
    }
}
