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

import ufpi.br.ufpimobile.ContatoListAdapter;
import ufpi.br.ufpimobile.R;
import ufpi.br.ufpimobile.model.Contato;

/**
 * Created by UFPI 236345 on 19/12/2017.
 * cria os contatos dos orgãos suplementares
 */

public class ContatoOrgaoSuplementaresActivity extends AppCompatActivity {

    private ListView listviewOrgaoSuple;
    private ContatoListAdapter adapter;
    private List<Contato> mContatosList;
    private Toolbar toolbar;

    /**
     * instancia novos contatos e coloca-os na list view dos org suplemen
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato_orgao_suplementares);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("Órgãos Suplementares");
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

        listviewOrgaoSuple = (ListView)findViewById(R.id.listviewOrgaoSuple);

        mContatosList = new ArrayList<>();
        //Add sample data for list
        //We can get data from DB, webservice here
        mContatosList.add(new Contato(15, "SRH","(86) 3215-5593","drh@ufpi.edu.br"));
        mContatosList.add(new Contato(16, "SCS","(86) 3237-1151","jacdourado@ufpi.edu.br"));
        mContatosList.add(new Contato(17, "COORDCOM","(86) 3215-5525","mauriciosantana@ufpi.edu.br"));
        mContatosList.add(new Contato(18, "EDUFPI","(86) 3215-5688","alaggior@uol.com.br"));
        mContatosList.add(new Contato(19, "STI","(86) 3215-5627","não possui"));
        mContatosList.add(new Contato(20, "PREUNI","(86) 3215-5604/-5605","preuni@ufpi.edu.br"));
        mContatosList.add(new Contato(21, "Bibliotecas","(86) 3237-1771","bccb@ufpi.edu.br"));
        mContatosList.add(new Contato(22, "HU - Hospital Universitário","(86) 3215-5988","ouvidoria.hupi@ebserh.gov.br"));
        mContatosList.add(new Contato(23, "HVU","(86) 3215-5537/5538","hvucpce@ufpi.edu.br"));
        mContatosList.add(new Contato(24, "Superintendência","(86) 3237-2211","coordenacao.ebtt@ufpi.edu.br"));








        //Init adapter
        adapter = new ContatoListAdapter(getApplicationContext(), mContatosList);
        listviewOrgaoSuple.setAdapter(adapter);

        listviewOrgaoSuple.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
