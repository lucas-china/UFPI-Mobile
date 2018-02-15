package ufpi.br.ufpimobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import ufpi.br.ufpimobile.Maps.*;
import ufpi.br.ufpimobile.controllers.DownloadJsonAsyncTask;
import ufpi.br.ufpimobile.controllers.JsonClass;
import ufpi.br.ufpimobile.controllers.TestConnection;
import ufpi.br.ufpimobile.model.GeoPointsDatabase;
import ufpi.br.ufpimobile.model.Node;


public class MapsInicio extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, DownloadJsonAsyncTask.AsyncResponse {


    private GeoPointsDatabase geoPointsDatabase = new GeoPointsDatabase(this);
    private TestConnection testaConexao;
    DownloadJsonAsyncTask asyncTask = new DownloadJsonAsyncTask();
    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_inicio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("UFPI Maps");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

         /*#################### Informações de conexão do JSON ###############*/
        testaConexao = new TestConnection(this);

        if (testaConexao.isConnected()) {
            Log.v("TestaConexao", "Está conectado");

            //manda o json para ser lido em uma asyncTask
            JsonClass.setContext(this);
            asyncTask.delegate = this;
            //Arquivo json hospedado no site da UFPI
            //Arquivo json contendo os nós e arestas para criação do grafo utilizando Dijkstra
            asyncTask.execute("https://infidel-gleams.000webhostapp.com/ufpimaps.json");
            gerarMapa();
        } else {
            Intent iniciarWifi = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
            //  criarTelaDeAlerta("Sem conexão", "Iniciar Conexão WiFi?", iniciarWifi, null, 1);
        }


    }

    private void gerarMapa() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.container_maps, new MapsActivity(), "MapsActivity");
        transaction.commitAllowingStateLoss();
    }

    private void atualizarMapa(Fragment fragment, String name) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container_maps, fragment, name);
        transaction.commit();
    }

    @Override
    public void processFinish(List<Node> nodes) {
        geoPointsDatabase.populateDatabase(nodes);
    }

    //Não precisa mexer ainda
    protected void onActivityResult(int tipoDeConexaoRequisitada, int resultado, Intent dadosRetornados) {
        super.onActivityResult(tipoDeConexaoRequisitada, resultado, dadosRetornados);
        final int TELA_ALERTA_TENTATIVA_1 = 1;
        final int TELA_ALERTA_TENTATIVA_2 = 2;
        if (tipoDeConexaoRequisitada == TELA_ALERTA_TENTATIVA_1) {
            if (resultado == RESULT_OK) {
                if (testaConexao.isConnected()) {

                } else {
                    Toast.makeText(this, "Sem conexão Wifi", Toast.LENGTH_LONG).show();
                    Intent iniciarRedesMoveis = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                    // criarTelaDeAlerta("Sem Conexão", "Iniciar Conexão via Redes Móveis?", iniciarRedesMoveis, null, TELA_ALERTA_TENTATIVA_2);
                }
            } else {
                Toast.makeText(this, "Sem conexão Wifi", Toast.LENGTH_LONG).show();
                Intent iniciarRedesMoveis = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                //criarTelaDeAlerta("Sem Conexão", "Iniciar Conexão via Redes Móveis?", iniciarRedesMoveis, null, TELA_ALERTA_TENTATIVA_2);
            }
        } else if (tipoDeConexaoRequisitada == TELA_ALERTA_TENTATIVA_2) {
            if (resultado == RESULT_OK) {
                if (testaConexao.isConnected()) {

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

    protected GeoPointsDatabase getGeoPointsDatabase() {
        return geoPointsDatabase;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.maps_inicio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.nav_satelite:
                break;
            case R.id.nav_mapa:
                break;
            case R.id.nav_hibrido:

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_home:
                Intent intent = new Intent(getApplicationContext(), TelaHome2.class);
                startActivity(intent);
                break;
            case R.id.nav_auditorio:
                atualizarMapa(new FragmentAuditorio(), "Auditorio");
                break;
            case R.id.nav_banheiros:
                atualizarMapa(new FragmentBanheiros(), "Banheiros");
                break;
            case R.id.nav_bibliotecas:
                atualizarMapa(new FragmentBiblioteca(), "Bibliotecas");
                break;
            case R.id.nav_centros:
                atualizarMapa(new FragmentCentros(), "Centros");
                break;
            case R.id.nav_cursos:
                atualizarMapa(new FragmentCursos(), "Cursos");
                break;
            case R.id.nav_departamentos:
                atualizarMapa(new FragmentDepartamentos(), "Departamentos");
                break;
            case R.id.nav_onibus:
                atualizarMapa(new FragmentOnibus(), "Estacoes de Onibus");
                break;
            case R.id.nav_laboratorios:
                atualizarMapa(new FragmentLaboratorios(), "Laboratorios");
                break;
            case R.id.nav_lanchonetes:
                atualizarMapa(new FragmentLanchonetes(), "Lanchonetes");
                break;
            case R.id.nav_orgaos:
                atualizarMapa(new FragmentOrgaos(), "Orgaos Centrais");
                break;
            case R.id.nav_posgraduacao:
                atualizarMapa(new FragmentPosGraducao(), "Pos-Graduacao");
                break;
            case R.id.nav_salas:
                atualizarMapa(new FragmentSalas(), "Salas de Aula");
                break;
            default:

                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
