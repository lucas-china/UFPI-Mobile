package ufpi.br.ufpimobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * Created by UFPI 236345 on 19/12/2017.
 * implementa a activity_contato que tem o toolbar e a list view
 */

public class ContatoActivity extends AppCompatActivity {


    String[] data;
    private Toolbar toolbar;

    /**
     * método que cria as instancias das classes filhas e dependendo da posição redireciona para a classe correta
     * @param savedInstanceState
     *
     */
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("Contatos UFPI");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(getApplicationContext(), TelaHome2.class);
                finish();
                startActivity(home);
            }
        });

        data = new String[]{"Reitoria", "Pro-reitorias", "Orgãos Suplementares", "Unidades Acadêmicas", "Coordenadorias de Graduação"};


        ListView listView = (ListView) findViewById(R.id.listViewContatoId);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);


        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0) {

                    Intent intent = new Intent();
                    intent.setClass(ContatoActivity.this, ContatoReitoriaActivity.class);
                    startActivity(intent);
                }
                if (position == 1) {
                    Intent intent = new Intent();
                    intent.setClass(ContatoActivity.this, ContatoProReitoriasActivity.class);
                    startActivity(intent);
                }
                if (position == 2) {
                    Intent intent = new Intent();
                    intent.setClass(ContatoActivity.this, ContatoOrgaoSuplementaresActivity.class);
                    startActivity(intent);
                }
                if (position == 3) {
                    Intent intent = new Intent();
                    intent.setClass(ContatoActivity.this, ContatoUniAcademicasActivity.class);
                    startActivity(intent);
                }
                if (position == 4) {
                    Intent intent = new Intent();
                    intent.setClass(ContatoActivity.this, ContatoCoordeGraduacaoActivity.class);
                    startActivity(intent);
                }
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
