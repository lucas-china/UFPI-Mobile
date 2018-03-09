package ufpi.br.ufpimobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class EscolherOnibus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolher_onibus);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_EscolherOnibus);
        toolbar.setTitle("Ônibus Disponíveis");
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

        final Button button = findViewById(R.id.ot);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Onibus.class);
                i.putExtra("bus","ot");
                startActivity(i);
            }
        });

        final Button button1 = findViewById(R.id.op);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Onibus.class);
                i.putExtra("bus","op");
                startActivity(i);
            }
        });

        final Button button2 = findViewById(R.id.obj);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Onibus.class);
                i.putExtra("bus","obj");
                startActivity(i);
            }
        });

        final Button button3 = findViewById(R.id.ctf);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Onibus.class);
                i.putExtra("bus","ctf");
                startActivity(i);
            }
        });

        final Button button4 = findViewById(R.id.ctbj);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Onibus.class);
                i.putExtra("bus","ctbj");
                startActivity(i);
            }
        });


    }
}
