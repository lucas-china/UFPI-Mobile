package ufpi.br.ufpimobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class CardapioSemana extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapio_semana);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_CardSemana);
        toolbar.setTitle("Cardápio da Semana");
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

        final Button button = findViewById(R.id.the);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), cardapioRU.class);
                i.putExtra("ru","the");
                startActivity(i);
            }
        });

        final Button button1 = findViewById(R.id.picos);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), cardapioRU.class);
                i.putExtra("ru","picos");
                startActivity(i);
            }
        });

        final Button button2 = findViewById(R.id.bj);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), cardapioRU.class);
                i.putExtra("ru","bj");
                startActivity(i);
            }
        });

        final Button button3 = findViewById(R.id.phb);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), cardapioRU.class);
                i.putExtra("ru","phb");
                startActivity(i);
            }
        });

        final Button button4 = findViewById(R.id.flo);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), cardapioRU.class);
                i.putExtra("ru","flo");
                startActivity(i);
            }
        });

    }
}
