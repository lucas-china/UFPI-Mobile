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

        data = new String[]{"Lucas Brito", "Vitor Cortez", "Rafael Henrique ", "Thiago Andrade", "Felipe Barro", "Carlos Cesar Pereira Lima Filho",
        "Fabio Gomes Pessoa", "Jassiléa Rocha Gois", "Paulo Henrique Rodrigues Silva", "Roney Lira", "Saulo de Társio", "Hugo Piauilino", "José de Oliveira",
        "Francisco Carlos", "Renato Souza", "Valeska Uchôa", "Alan Ribeiro", "Orlando Amorim", "Rafael Martins Barros", "Selles Gustavo", "Roberson Costa",
        "Jardeson Barbosa", "Giovanni Emanuel", "prof. Ms. Armando Soares", "prof Dr. Ricardo Lira", "prof. Dr. Valdemir Junior"};

        ListView listView = (ListView) findViewById(R.id.listaDevs);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);


        listView.setAdapter(adapter);
    }
}
