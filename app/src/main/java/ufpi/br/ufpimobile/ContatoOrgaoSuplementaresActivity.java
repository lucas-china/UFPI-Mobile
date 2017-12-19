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

import ufpi.br.ufpimobile.ContatoListAdapter;
import ufpi.br.ufpimobile.R;
import ufpi.br.ufpimobile.model.Contato;

/**
 * Created by UFPI 236345 on 19/12/2017.
 */

public class ContatoOrgaoSuplementaresActivity extends AppCompatActivity {

    private ListView listviewOrgaoSuple;
    private ContatoListAdapter adapter;
    private List<Contato> mContatosList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato_orgao_suplementares);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listviewOrgaoSuple = (ListView)findViewById(R.id.listviewOrgaoSuple);

        mContatosList = new ArrayList<>();
        //Add sample data for list
        //We can get data from DB, webservice here
        mContatosList.add(new Contato(15, "SRH","(86) 3215-5593","drh@ufpi.edu.br"));
        mContatosList.add(new Contato(16, "SCS","(86) 3237-1151","jacdourado@ufpi.edu.br"));
        mContatosList.add(new Contato(17, "COORDCOM","(86) 3215-5525","mauriciosantana@ufpi.edu.br"));
        mContatosList.add(new Contato(18, "EDUFPI","(86) 3215-5688","alaggior@uol.com.br"));
        mContatosList.add(new Contato(19, "NTI","(86) 3215-5627","preuni@ufpi.edu.br"));
        mContatosList.add(new Contato(20, "PREUNI","(86) 3215-5604/-5605","bccb@ufpi.edu.br"));
        mContatosList.add(new Contato(21, "Bibliotecas","(86) 3237-1771","ouvidoria.hupi@ebserh.gov.br"));
        mContatosList.add(new Contato(22, "HU - Hospital Universitário","(86) 3215-5988","hvucpce@ufpi.edu.br"));
        mContatosList.add(new Contato(23, "HVU","(86) 3215-5537/5538","não possui"));








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
