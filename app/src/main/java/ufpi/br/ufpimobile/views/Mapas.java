package ufpi.br.ufpimobile.views;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import ufpi.br.ufpimobile.R;
import ufpi.br.ufpimobile.controllers.TestConnection;
import ufpi.br.ufpimobile.model.ApplicationObject;
import ufpi.br.ufpimobile.model.Edge;
import ufpi.br.ufpimobile.model.GeoPointsDatabase;
import ufpi.br.ufpimobile.model.Graph;
import ufpi.br.ufpimobile.model.Node;

import java.util.ArrayList;
import java.util.List;

//import com.firebase.client.DataSnapshot;
//import com.firebase.client.Firebase;
//import com.firebase.client.FirebaseError;
//import com.firebase.client.ValueEventListener;

/**
 * Classe Main Activy que gerencia a interface principal da aplicacao e delega as atividades do
 * Drawer ao Navigation Drawer
 * Created by HugoPiauilino on 12/03/15.
 */
public class Mapas extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
        AnchorsFragment.OnFragmentInteractionListener, DownloadJsonAsyncTask.AsyncResponse {


    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private int mCurrentSelectedPosition = 2;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    private GeoPointsDatabase geoPointsDatabase = new GeoPointsDatabase(this);
    private TestConnection testaConexao;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private List<Node> nodes = new ArrayList<Node>();
    private List<Edge> edges = new ArrayList<Edge>();
    private Graph grafo = new Graph(nodes, edges);


    DownloadJsonAsyncTask asyncTask = new DownloadJsonAsyncTask();

    /**
     * Metodo executado na criacao da activity main (principal) e seta todos os parametros
     * necessarios para a sua execucao
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mapas);

        testaConexao = new TestConnection(this);

        mDrawerList = (ListView) findViewById(R.id.navigation_drawer);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        addDrawerItems();
        setupDrawer();

        mActivityTitle = getTitle().toString();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        if (testaConexao.isConnected()) {
            Log.v("TestaConexao", "Está conectado");

            //manda o json para ser lido em uma asyncTask
            JsonClass.setContext(this);
            asyncTask.delegate = this;
            //Arquivo json hospedado no site da UFPI
            //Arquivo json contendo os nós e arestas para criação do grafo utilizando Dijkstra
            asyncTask.execute("https://infidel-gleams.000webhostapp.com/ufpimaps.json");

            geraMapa();
        } else {
            Intent iniciarWifi = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
            criarTelaDeAlerta("Sem conexão", "Iniciar Conexão WiFi?", iniciarWifi, null, 1);
        }
    }

    @Override
    public void processFinish(List<Node> nodes) {
        geoPointsDatabase.populateDatabase(nodes);
        grafo.createGraphWithNodes(nodes);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getSupportActionBar().setTitle("UFPI Maps");
    }

    private void addDrawerItems() {
        String[] titles = {
                getString(R.string.title_section_anchors),
                getString(R.string.title_section_trace_routes),
                getString(R.string.title_section_normal_map),
                getString(R.string.title_section_satelite_map),
                getString(R.string.title_section_hibrid_map),
                getString(R.string.title_section_about)
        };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titles);
        mDrawerList.setAdapter(mAdapter);
        mDrawerList.setItemChecked(mCurrentSelectedPosition, true);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCurrentSelectedPosition = position;
                selectItem(position);

            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("UFPI Maps");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                mActivityTitle = mAdapter.getItem(mCurrentSelectedPosition);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerList.setItemChecked(mCurrentSelectedPosition, true);

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void criarTelaDeAlerta(String titulo, String mensagem, Intent acaoPositiva, Intent acaoNegativa, int tentativa) {
        Intent iniciarTelaDeAlerta = new Intent(this, AlertScreen.class);
        iniciarTelaDeAlerta.putExtra("titulo", titulo);
        iniciarTelaDeAlerta.putExtra("mensagem", mensagem);
        iniciarTelaDeAlerta.putExtra("acaoPositiva", acaoPositiva);
        iniciarTelaDeAlerta.putExtra("acaoNegativa", acaoNegativa);
        startActivityForResult(iniciarTelaDeAlerta, tentativa);
    }

    @Override
    protected void onActivityResult(int tipoDeConexaoRequisitada, int resultado, Intent dadosRetornados) {
        super.onActivityResult(tipoDeConexaoRequisitada, resultado, dadosRetornados);
        final int TELA_ALERTA_TENTATIVA_1 = 1;
        final int TELA_ALERTA_TENTATIVA_2 = 2;
        if (tipoDeConexaoRequisitada == TELA_ALERTA_TENTATIVA_1) {
            if (resultado == RESULT_OK) {
                if (testaConexao.isConnected()) {
                    geraMapa();
                } else {
                    Toast.makeText(this, "Sem conexão Wifi", Toast.LENGTH_LONG).show();
                    Intent iniciarRedesMoveis = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                    criarTelaDeAlerta("Sem Conexão", "Iniciar Conexão via Redes Móveis?", iniciarRedesMoveis, null, TELA_ALERTA_TENTATIVA_2);
                }
            } else {
                Toast.makeText(this, "Sem conexão Wifi", Toast.LENGTH_LONG).show();
                Intent iniciarRedesMoveis = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                criarTelaDeAlerta("Sem Conexão", "Iniciar Conexão via Redes Móveis?", iniciarRedesMoveis, null, TELA_ALERTA_TENTATIVA_2);
            }
        } else if (tipoDeConexaoRequisitada == TELA_ALERTA_TENTATIVA_2) {
            if (resultado == RESULT_OK) {
                if (testaConexao.isConnected()) {
                    geraMapa();
                } else {
                    Toast.makeText(this, "Sem conexão com a internet", Toast.LENGTH_LONG).show();
                    finish();
                }
            } else {
                Toast.makeText(this, "Sem conexão com a internet", Toast.LENGTH_LONG).show();
                finish();
            }
        }

    }

    private void geraMapa() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new MapFragment())
                .commit();
    }

    /**
     * Metodo que recebe indica o item selecionado no Navigation Drawer e substitui o fragmento
     * que e representado por essa posicao
     *
     * @param position Posicao selecionada no Navigation Drawer
     */
    @Override
    public void onNavigationDrawerItemSelected(int position) {

        if (position == 0) {
            AnchorsFragment anchorsFragment = new AnchorsFragment();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            //ft.addToBackStack(null); //Para o botao voltar nao sair da aplicacao
            ft.replace(R.id.container, anchorsFragment, "anchorsFragment");
            ft.commit();
        } else if (position == 1) {
            TraceRouteFragment traceRouteFragment = new TraceRouteFragment();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            //ft.addToBackStack(null); //Para o botao voltar nao sair da aplicacao
            ft.replace(R.id.container, traceRouteFragment, "traceRouteFragment");
            ft.commit();
        } else if (position == 2) {
            MapFragment mapFragment = new MapFragment();
            ((ApplicationObject) getApplication()).setMap(mapFragment);
            FragmentTransaction ft = fragmentManager.beginTransaction();
            mapFragment.setTipoDeMapa(position);
            ft.replace(R.id.container, mapFragment, "mapFragment");
            ft.commit();
        } else if (position == 3) {
            MapFragment mapFragment = new MapFragment();
            ((ApplicationObject) getApplication()).setMap(mapFragment);
            FragmentTransaction ft = fragmentManager.beginTransaction();
            mapFragment.setTipoDeMapa(position);
            ft.replace(R.id.container, mapFragment, "mapFragment");
            ft.commit();
        } else if (position == 4) {
            MapFragment mapFragment = new MapFragment();
            ((ApplicationObject) getApplication()).setMap(mapFragment);
            FragmentTransaction ft = fragmentManager.beginTransaction();
            mapFragment.setTipoDeMapa(position);
            ft.replace(R.id.container, mapFragment, "mapFragment");
            ft.commit();
        } else if (position == 5) {
            AboutFragment aboutFragment = new AboutFragment();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            //ft.addToBackStack(null); //Para o botao voltar nao sair da aplicacao
            ft.replace(R.id.container, aboutFragment, "aboutFragment");
            ft.commit();
        }
    }

    /**
     * Metodo que recebe as interacoes do fragment
     *
     * @param id
     */
    @Override
    public void onFragmentInteraction(String id) {

    }

    protected GeoPointsDatabase getGeoPointsDatabase() {
        return geoPointsDatabase;
    }

    protected Graph getGrafo() {
        return grafo;
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    private void selectItem(int position) {
        if (mDrawerList != null) {
            mDrawerList.setItemChecked(position, true);
        }
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawers();
        }
        onNavigationDrawerItemSelected(position);
    }


}