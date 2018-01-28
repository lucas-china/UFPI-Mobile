package ufpi.br.ufpimobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Ensino extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ensino);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_Ensino);
        toolbar.setTitle("Ensino");
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

        final Button button = findViewById(R.id.tec);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Tecnico.class);
                startActivity(i);
            }
        });

        final Button button1 = findViewById(R.id.grad);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Graduacao.class);
                startActivity(i);
            }
        });

        final Button button2 = findViewById(R.id.stricto);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), StrictoSensu.class);
                startActivity(i);
            }
        });

        final Button button3 = findViewById(R.id.lato);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LatoSensu.class);
                startActivity(i);
            }
        });
    }
}
