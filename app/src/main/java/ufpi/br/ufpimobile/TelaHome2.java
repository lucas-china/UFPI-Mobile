package ufpi.br.ufpimobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

public class TelaHome2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_home2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("UFPI Mobile");
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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

        this.mViewHolder.imageButton_inf = findViewById(R.id.im_inf);

        this.mViewHolder.imageButton_inf.setOnClickListener(this);

        this.mViewHolder.imageButton_evento = findViewById(R.id.im_evento);

        this.mViewHolder.imageButton_evento.setOnClickListener(this);

        this.mViewHolder.imageButton_mapa = findViewById(R.id.im_mapa);

        this.mViewHolder.imageButton_mapa.setOnClickListener(this);

        this.mViewHolder.imageButton_sigaa = findViewById(R.id.im_sigaa);

        this.mViewHolder.imageButton_sigaa.setOnClickListener(this);

        this.mViewHolder.imageButton_manual = findViewById(R.id.im_manual);

        this.mViewHolder.imageButton_manual.setOnClickListener(this);

        this.mViewHolder.imageButton_ensino = findViewById(R.id.im_ensino);

        this.mViewHolder.imageButton_ensino.setOnClickListener(this);

        this.mViewHolder.imageButton_onibus = findViewById(R.id.im_onibus);

        this.mViewHolder.imageButton_onibus.setOnClickListener(this);
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

    /*
        classe que agrupa os elementos da activity e é instanciada lá em cima
     */
    private static class ViewHolder {

        ImageButton imageButton_contato;
        ImageButton imageButton_calendario;
        ImageButton imageButton_sigaa;
        ImageButton imageButton_restaurante;
        ImageButton imageButton_inf;
        ImageButton imageButton_radio;
        ImageButton imageButton_noticia;
        ImageButton imageButton_evento;
        ImageButton imageButton_mapa;
        ImageButton imageButton_manual;
        ImageButton imageButton_ensino;
        ImageButton imageButton_onibus;

    }

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
            case R.id.im_inf:
                Intent intent1 = new Intent(getApplicationContext(), Informacao.class);
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
                Intent intent4 = new Intent(getApplicationContext(), EscolherCalendario.class);
                startActivity(intent4);
                break;
            case R.id.im_restaurante:
                Intent intent5 = new Intent(getApplicationContext(), RestauranteActivity.class);
                startActivity(intent5);
                break;
            case R.id.im_mapa:
                Intent intent6 = new Intent(getApplicationContext(), MapsInicio.class);
                startActivity(intent6);
                break;
            case R.id.im_evento:
                Intent intent7 = new Intent(getApplicationContext(), Eventos.class);
                startActivity(intent7);
                break;
            case R.id.im_manual:
                Intent intent8 = new Intent(getApplicationContext(), ManualCalouro.class);
                startActivity(intent8);
                break;
            case R.id.im_ensino:
                Intent intent9 = new Intent(getApplicationContext(), Ensino.class);
                startActivity(intent9);
                break;
            case R.id.im_onibus:
                Intent intent10 = new Intent(getApplicationContext(), Onibus.class);
                startActivity(intent10);
                break;
            default:
                Intent intenterro = new Intent(getApplicationContext(), ErroActivity.class);
                startActivity(intenterro);
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tela_home2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_sobre) {
            Intent intent = new Intent(getApplicationContext(), SobreNos.class);
            startActivity(intent);
        } else if (id == R.id.nav_colaboradores) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
