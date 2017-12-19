package ufpi.br.ufpimobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ufpi.br.ufpimobile.model.Contato;

/**
 * Created by UFPI 236345 on 19/12/2017.
 *
 */

public class ContatoReitoriaActivity extends AppCompatActivity {


    private ListView listviewReitoria;
    private ContatoListAdapter adapter;
    private List<Contato> mContatosList;
    private  Toolbar toolbar;


    /**
     * instancia novos contatos e coloca-os na list view da reitoria
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato_reitoria);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("Contatos UFPI");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(getApplicationContext(), ContatoActivity.class);
                finish();
                startActivity(home);
            }
        });

        listviewReitoria = (ListView)findViewById(R.id.listviewReitoria);

        mContatosList = new ArrayList<>();
        //Add sample data for list
        //We can get data from DB, webservice here
        mContatosList.add(new Contato(1, "Reitor","(86) 3215-5510/5511","reitor@ufpi.edu.br"));
        mContatosList.add(new Contato(3, "Chefe de gabinete","(86) 3215-5512","reitor@ufpi.edu.br"));
        mContatosList.add(new Contato(4, "Secretária Adjunta","(86) 3215-5510/5511","secretariareitor@ufpi.edu.br"));
        mContatosList.add(new Contato(5, "Vice-Reitora","(86) 3215-5527","vicereitoria@ufpi.edu.br"));
        mContatosList.add(new Contato(6, "Assistente da Vice-Reitoria","(86) 3215-5528","vicereitoria@ufpi.edu.br"));
        mContatosList.add(new Contato(7, "COPESE","(86) 3215-5556/5656","copese@ufpi.edu.br"));



        //Init adapter
        adapter = new ContatoListAdapter(getApplicationContext(), mContatosList);
        listviewReitoria.setAdapter(adapter);

        listviewReitoria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Do something
                //Ex: display msg with product id get from view.getTag
                //Toast.makeText(getApplicationContext(), "Contato id =" + view.getTag(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * implementa o voltar pra home
     * @param item
     * @return true e volta pra tela home
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
