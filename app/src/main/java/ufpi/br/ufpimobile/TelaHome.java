package ufpi.br.ufpimobile;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import ufpi.br.ufpimobile.controllers.DownloadJsonAsyncTask;
import ufpi.br.ufpimobile.model.GeoPointsDatabase;
import ufpi.br.ufpimobile.model.Node;
import ufpi.br.ufpimobile.controllers.TestConnection;
import ufpi.br.ufpimobile.controllers.JsonClass;

import java.util.List;



public class TelaHome extends AppCompatActivity implements View.OnClickListener, DownloadJsonAsyncTask.AsyncResponse{


    private ViewHolder mViewHolder = new ViewHolder();
    private Toolbar toolbarlayout;

    private GeoPointsDatabase geoPointsDatabase = new GeoPointsDatabase(this);
    private TestConnection testaConexao;
    DownloadJsonAsyncTask asyncTask = new DownloadJsonAsyncTask();
    private FragmentManager fragmentManager = getSupportFragmentManager();


    /*
        metódo de criação
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_home);

        toolbarlayout = (Toolbar) findViewById(R.id.toolbar);
        toolbarlayout.setTitle("UFPI Mobile");
        setSupportActionBar(toolbarlayout);


        //recupera as informações da image view

        this.mViewHolder.imageButton_noticia = findViewById(R.id.im_noticias);

        this.mViewHolder.imageButton_noticia.setOnClickListener(this);

        this.mViewHolder.imageButton_calendario = findViewById(R.id.im_calendario);

        this.mViewHolder.imageButton_calendario.setOnClickListener(this);

        this.mViewHolder.imageButton_contato = findViewById(R.id.im_contatos);

        this.mViewHolder.imageButton_contato.setOnClickListener(this);

        this.mViewHolder.imageButton_radio = findViewById(R.id.im_radio);

        this.mViewHolder.imageButton_radio.setOnClickListener(this);

        this.mViewHolder.imageButton_restaurante = findViewById(R.id.im_restaurante);

        this.mViewHolder.imageButton_restaurante.setOnClickListener(this);

        this.mViewHolder.imageButton_sobre = findViewById(R.id.im_sobre);

        this.mViewHolder.imageButton_sobre.setOnClickListener(this);

        this.mViewHolder.imageButton_evento = findViewById(R.id.im_evento);

        this.mViewHolder.imageButton_evento.setOnClickListener(this);

        this.mViewHolder.imageButton_mapa = findViewById(R.id.im_mapa);

        this.mViewHolder.imageButton_mapa.setOnClickListener(this);

        this.mViewHolder.imageButton_sigaa = findViewById(R.id.im_sigaa);

        this.mViewHolder.imageButton_sigaa.setOnClickListener(this);

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
            //geraMapa();
        } else {
            Intent iniciarWifi = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
            //  criarTelaDeAlerta("Sem conexão", "Iniciar Conexão WiFi?", iniciarWifi, null, 1);
        }
    }
    /*
        metodo que implementa a açao do click do botao
    */

    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id) {
            case R.id.im_noticias:
                Intent intent = new Intent(getApplicationContext(), MostrarNoticias.class);
                startActivity(intent);
                //System.out.println("Hello world");
                break;
            case R.id.im_contatos:
                Intent intent0 = new Intent(getApplicationContext(), ContatoActivity.class);
                startActivity(intent0);
                break;
            case R.id.im_sobre:
                Intent intent1 = new Intent(getApplicationContext(), SobreNos.class);
                startActivity(intent1);
                break;
            case R.id.im_radio:
                Intent intent2 = new Intent(getApplicationContext(), Radio.class);
                startActivity(intent2);
                break;
            case R.id.im_sigaa:
                Intent intent3 = new Intent(getApplicationContext(), Sigaa.class);
                startActivity(intent3);
                break;
            case R.id.im_calendario:
                Intent intent4 = new Intent(getApplicationContext(), Calendario.class);
                startActivity(intent4);
                break;
            case R.id.im_restaurante:
                Intent intent5 = new Intent(getApplicationContext(), RestauranteActivity.class);
                startActivity(intent5);
                break;
            case R.id.im_mapa:
                Intent intent6 = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent6);
                break;
            default:
                Intent intenterro = new Intent(getApplicationContext(), ErroActivity.class);
                startActivity(intenterro);
        }
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


    /*
        classe que agrupa os elementos da activity e é instanciada lá em cima
     */
    private static class ViewHolder {

        ImageButton imageButton_contato;
        ImageButton imageButton_calendario;
        ImageButton imageButton_sigaa;
        ImageButton imageButton_restaurante;
        ImageButton imageButton_sobre;
        ImageButton imageButton_radio;
        ImageButton imageButton_noticia;
        ImageButton imageButton_evento;
        ImageButton imageButton_mapa;

    }
}
