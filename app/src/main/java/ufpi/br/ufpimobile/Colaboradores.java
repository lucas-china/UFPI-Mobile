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

        data = new String[]{"Lucas Santana Brito - DC/CCN/UFPI", "Vitor Augusto Correa Cortez Almeida  - PPGCC/UFPI", "Pedro Henrique Pinto Albuquerque - DC/CCN/UFPI" ,"Rafael Henrique Mendes Monteiro da Silva - DC/CCN/UFPI", "Thiago Andrade Borges - DC/CCN/UFPI",
                "Felipe Barros Caminha - DC/CCN/UFPI", "Carlos Cesar Pereira Lima Filho - CTT/UFPI", "Fabio Gomes Pessoa - CTT/UFPI", "Jassiléa Rocha Gois - CTT/UFPI", "Paulo Henrique Rodrigues Silva - CTT/UFPI", "Roney Lira de Seles Santos - DC/CCN/UFPI",
                "Saulo de Társio Silva Sousa - DC/CCN/UFPI", "Hugo Santos Piauilino Neto - PPGCC/UFPI", "José de Oliveira Lins Neto - PPGCC/UFPI", "Francisco Carlos Silva Junior - PPGCC/UFPI", "Renato Souza  - DC/CCN/UFPI", "Valeska de Sousa Uchôa - PPGCC/UFPI",
                "Alan Ribeiro Andrade - DC/CCN/UFPI", "Orlando Amorim Leite Filho - DC/CCN/UFPI", "Rafael Martins Barros - DC/CCN/UFPI", "Selles Gustavo Ferreira Carvalho Araújo - PPGCC/UFPI",
                "Roberson Costa - DC/CCN/UFPI", "Jardeson Leandro Nascimento Barbosa - PPGCC/UFPI", "Giovanni Emanuel Vieira da Silva Santana - DC/CCN/UFPI","prof. Dr. Antônio Oseas de Carvalho Filho - CSHNB/UFPI","prof. Msc. Alcilene Dalila de Sousa - CSHNB/UFPI" ,
                "prof. Msc. Armando Soares Sousa - DC/CCN/UFPI", "prof Dr. Ricardo de Andrade Lira Rabelo - DC/CCN/UFPI", "prof. Dr. José Valdemir dos Reis Junior - CTT/UFPI"};

        ListView listView = (ListView) findViewById(R.id.listaDevs);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);


        listView.setAdapter(adapter);
    }
}
