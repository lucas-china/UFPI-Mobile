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
 */

public class ContatoProReitoriasActivity extends AppCompatActivity{

    private ListView listviewProReitoria;
    private ContatoListAdapter adapter;
    private List<Contato> mContatosList;
    private Toolbar toolbar;

    /**
     * instancia novos contatos e coloca-os na list view da pro reitoria
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato_pro_reitorias);
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
        listviewProReitoria = (ListView)findViewById(R.id.listviewProReitoria);

        mContatosList = new ArrayList<>();
        //Add sample data for list
        //We can get data from DB, webservice here
        mContatosList.add(new Contato(8, "PRAD","(86) 3215-5580/5581","prad.adm@ufpi.edu.br"));
        mContatosList.add(new Contato(9, "PRAEC","(86) 3215-5640","praec.adm@ufpi.edu.br"));
        mContatosList.add(new Contato(10, "PREG","(86) 3215-5540","preg@ufpi.edu.br"));
        mContatosList.add(new Contato(11, "PRÓ-PESQ","(86) 3215-5564","pesquisa@edu.ufpi.br"));
        mContatosList.add(new Contato(12, "PROPLAN","(86) 3215-5620/5621","proplan@ufpi.edu.br"));
        mContatosList.add(new Contato(13, "PREX","(86) 3215-5571/5570","prex@ufpi.edu.br"));
        mContatosList.add(new Contato(14, "PRPG","(86) 3237-1410","prpg@ufpi.edu.br"));




        //Init adapter
        adapter = new ContatoListAdapter(getApplicationContext(), mContatosList);
        listviewProReitoria.setAdapter(adapter);

        listviewProReitoria.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            /**
             *
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
