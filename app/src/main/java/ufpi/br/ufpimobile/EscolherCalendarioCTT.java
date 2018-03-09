package ufpi.br.ufpimobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class EscolherCalendarioCTT extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolher_calendario_ctt);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_EscolherCalendarioCTT);
        toolbar.setTitle("Calendários Disponíveis");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(getApplicationContext(), EscolherCalendario.class);
                finish();
                startActivity(home);
            }
        });

        final Button button = findViewById(R.id.cttTHE);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CalendarioCTT.class);
                i.putExtra("ctt","the");
                startActivity(i);
            }
        });

        final Button button1 = findViewById(R.id.cttBJ);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CalendarioCTT.class);
                i.putExtra("ctt","bj");
                startActivity(i);
            }
        });

        final Button button2 = findViewById(R.id.cttFlo);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CalendarioCTT.class);
                i.putExtra("ctt","flo");
                startActivity(i);
            }
        });
    }
}
