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

        data = new String[]{"Lucas Santana Brito - DC/CCN", "Vitor Augusto Correa Cortez Almeida  - DC/CCN", "Pedro Henrique Pinto Albuquerque - DC/CCN" ,"Rafael Henrique Mendes Monteiro da Silva - DC/CCN", "Thiago Andrade Borges - DC/CCN",
                "Felipe Barros Caminha - DC/CCN", "Carlos Cesar Pereira Lima Filho - CTT/UFPI", "Fabio Gomes Pessoa - CTT/UFPI", "Jassiléa Rocha Gois - CTT/UFPI", "Paulo Henrique Rodrigues Silva - CTT/UFPI", "Roney Lira de Seles Santos - DC/UFPI",
                "Saulo de Társio Silva Sousa - DC/UFP", "Hugo Santos Piauilino Neto - DC/UFP", "José de Oliveira Lins Neto - DC/UFP", "Francisco Carlos Silva Junior - DC/UFP", "Renato Souza - DC/UFP", "Valeska de Sousa Uchôa - DC/UFP",
                "Alan Ribeiro Andrade - DC/UFP", "Orlando Amorim Leite Filho - DC/UFP", "Rafael Martins Barros - DC/UFP", "Selles Gustavo Ferreira Carvalho Araújo - DC/UFP",
                "Roberson Costa - DC/UFP", "Jardeson Leandro Nascimento Barbosa - DC/UFP", "Giovanni Emanuel Vieira da Silva Santana - DC/UFP","prof. Dr. Antônio Oseas de Carvalho Filho - CSHNB","prof. Msc. Alcilene Dalila de Sousa - CSHNB" ,
                "prof. Msc. Armando Soares Sousa - DC/CCN", "prof Dr. Ricardo de Andrade Lira Rabelo - DC/CCN", "prof. Dr. José Valdemir dos Reis Junior - CTT/UFPI"};

        ListView listView = (ListView) findViewById(R.id.listaDevs);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);


        listView.setAdapter(adapter);
    }
}
