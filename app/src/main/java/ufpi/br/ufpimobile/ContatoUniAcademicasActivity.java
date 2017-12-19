package ufpi.br.ufpimobile;

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

public class ContatoUniAcademicasActivity extends AppCompatActivity {

    private ListView listviewUnidadesAca;
    private ContatoListAdapter adapter;
    private List<Contato> mContatosList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato_uni_academicas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listviewUnidadesAca = (ListView)findViewById(R.id.listviewUnidadesAcad);

        mContatosList = new ArrayList<>();
        //Add sample data for list
        //We can get data from DB, webservice here
        mContatosList.add(new Contato(24, "CCA","(86) 3215-5740","cca_diretoria@ufpi.eu.br"));
        mContatosList.add(new Contato(25, "CCE","(86) 3215-5693/5810","cce.secretaria@ufpi.edu.br"));
        mContatosList.add(new Contato(26, "CCN","(86) 3215-5843/5692","mmoura@ufpi.edu.br"));
        mContatosList.add(new Contato(27, "CCS","(86) 3215-5696","ccsdiretoria@ufpi.edu.br"));
        mContatosList.add(new Contato(28, "CCHL","(86) 3215-5770/5771","nelsonmatos@ig.com.br"));
        mContatosList.add(new Contato(29, "CT","(86) 3215-5698/5699","ct@ufpi.br"));


        adapter = new ContatoListAdapter(getApplicationContext(), mContatosList);
        listviewUnidadesAca.setAdapter(adapter);

        listviewUnidadesAca.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Do something
                //Ex: display msg with product id get from view.getTag
                //Toast.makeText(getApplicationContext(), "Contato id =" + view.getTag(), Toast.LENGTH_SHORT).show();
            }
        });
    }
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
