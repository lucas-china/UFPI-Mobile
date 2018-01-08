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
                Intent home = new Intent(getApplicationContext(), TelaHome.class);
                finish();
                startActivity(home);
            }
        });

        final Button button = findViewById(R.id.seg);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), VerCardapio.class);
                i.putExtra("dia",1);
                startActivity(i);
            }
        });

        final Button button1 = findViewById(R.id.ter);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), VerCardapio.class);
                i.putExtra("dia",2);
                startActivity(i);
            }
        });

        final Button button2 = findViewById(R.id.qua);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), VerCardapio.class);
                i.putExtra("dia",3);
                startActivity(i);
            }
        });

        final Button button3 = findViewById(R.id.qui);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), VerCardapio.class);
                i.putExtra("dia",4);
                startActivity(i);
            }
        });

        final Button button4 = findViewById(R.id.sex);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), VerCardapio.class);
                i.putExtra("dia",5);
                startActivity(i);
            }
        });

        final Button button5 = findViewById(R.id.sab);
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), VerCardapio.class);
                i.putExtra("dia",6);
                startActivity(i);
            }
        });
    }
}
