package ufpi.br.ufpimobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Colaboradores extends AppCompatActivity {

    String[] data;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colaboradores);

        toolbar = (Toolbar) findViewById(R.id.toolbar_Colab);
        toolbar.setTitle("Colaboradores UFPI Mobile");
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

        data = new String[]{"Lucas Santana Brito", "Vitor Augusto Correa Cortez Almeida", "Pedro Henrique Pinto Albuquerque" ,"Rafael Henrique Mendes Monteiro da Silva", "Thiago Andrade Borges", "Felipe Barros Caminha",
                "Carlos Cesar Pereira Lima Filho", "Fabio Gomes Pessoa", "Jassiléa Rocha Gois", "Paulo Henrique Rodrigues Silva", "Roney Lira de Seles Santos", "Saulo de Társio Silva Sousa",
                "Hugo Santos Piauilino Neto", "José de Oliveira Lins Neto", "Francisco Carlos Silva Junior", "Renato Souza", "Valeska de Sousa Uchôa", "Alan Ribeiro Andrade",
                "Orlando Amorim Leite Filho", "Rafael Martins Barros", "Selles Gustavo Ferreira Carvalho Araújo",
                "Roberson Costa", "Jardeson Leandro Nascimento Barbosa", "Giovanni Emanuel Vieira da Silva Santana", "prof. Ms. Armando Soares Sousa", "prof Dr. Ricardo de Andrade Lira Rabelo",
                "prof. Dr. José Valdemir dos Reis Junior"};

        ListView listView = (ListView) findViewById(R.id.listaDevs);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);


        listView.setAdapter(adapter);
    }
}
