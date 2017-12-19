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
 * implementa os contatos da classe filha  cood graduação
 */

public class ContatoCoordeGraduacaoActivity extends AppCompatActivity {

    private ListView listviewCordGradu;
    private ContatoListAdapter adapter;
    private List<Contato> mContatosList;
    private Toolbar toolbar;


    /**
     * instancia os contatos dos departamentos
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato_coorde_graduacao);
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

        listviewCordGradu = (ListView)findViewById(R.id.listviewCoordGraduacao);

        mContatosList = new ArrayList<>();
        //Add sample data for list
        //We can get data from DB, webservice here
        mContatosList.add(new Contato(30, "Agronomia","(86) 3215-5751","não possui"));
        mContatosList.add(new Contato(31, "Medicina Veterinária","(86) 3215-5752","não possui"));
        mContatosList.add(new Contato(32, "Comunicação Social","(86) 3215-5963","não possui"));
        mContatosList.add(new Contato(33, "Artes Visuais","(86) 3215-5815","não possui"));
        mContatosList.add(new Contato(34, "Música","(86) 3215-5823","não possui"));
        mContatosList.add(new Contato(35, "Pedagogia","(86) 3215-5817","não possui"));
        mContatosList.add(new Contato(36, "Biologia","(86) 3215-5831","não possui"));
        mContatosList.add(new Contato(37, "Ciências da Computação","(86) 3215-5839","não possui"));
        mContatosList.add(new Contato(38, "Física","(86) 3215-5834","não possui"));
        mContatosList.add(new Contato(39, "Matemática","(86) 3215-5836","não possui"));
        mContatosList.add(new Contato(40, "Química","(86) 3215-5844","não possui"));
        mContatosList.add(new Contato(41, "Educação Física","(86) 3215-5861","não possui"));
        mContatosList.add(new Contato(42, "Enfermagem","(86) 3215-5881","não possui"));
        mContatosList.add(new Contato(43, "Farmácia","(86) 3215-5870","não possui"));
        mContatosList.add(new Contato(44, "Medicina","(86) 3215-5855","não possui"));
        mContatosList.add(new Contato(45, "Administração","(86) 3215-5791","não possui"));
        mContatosList.add(new Contato(46, "Ciências Sociais","(86) 3215-5781","não possui"));
        mContatosList.add(new Contato(47, "Ciências Contábeis","(86) 3215-5792","não possui"));
        mContatosList.add(new Contato(48, "Direito","(86) 3215-5776","não possui"));
        mContatosList.add(new Contato(49, "Economia","(86) 3215-5789","não possui"));
        mContatosList.add(new Contato(50, "Filosofia","(86) 3215-5787","não possui"));
        mContatosList.add(new Contato(51, "Geografia","(86) 3215-5778","não possui"));
        mContatosList.add(new Contato(52, "História","(86) 3215-5779","não possui"));
        mContatosList.add(new Contato(53, "Letras","(86) 3215-5783","não possui"));
        mContatosList.add(new Contato(54, "Serviço Social","(86) 3215-5785","não possui"));
        mContatosList.add(new Contato(55, "Ciência Política","(86) 3237-2080","não possui"));
        mContatosList.add(new Contato(56, "Arquitetura e Urbanismo","(86) 3215-5725","não possui"));
        mContatosList.add(new Contato(57, "Engenharia Civil","(86) 3215-5726","não possui"));
        mContatosList.add(new Contato(58, "Engenharia de Produção","(86) 3237-2212","não possui"));
        mContatosList.add(new Contato(59, "Engenharia Elétrica","(86) 3237-1565","não possui"));
        mContatosList.add(new Contato(60, "Engenharia Mecânica","(86) 3237-1199","não possui"));
        mContatosList.add(new Contato(61, "Engenharia de Agrimensura","(86) 3215-5708","não possui"));



        //Init adapter
        adapter = new ContatoListAdapter(getApplicationContext(), mContatosList);
        listviewCordGradu.setAdapter(adapter);

        listviewCordGradu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
