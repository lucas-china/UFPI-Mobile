package ufpi.br.ufpimobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;

public class Informacao extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacao);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_Informacao);
        toolbar.setTitle("Acesso a Informação");
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

        final Button button = findViewById(R.id.sic);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SIC.class);
                startActivity(i);
            }
        });

        final Button button1 = findViewById(R.id.dadosAbertos);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), DadosAbertos.class);
                startActivity(i);
            }
        });
    }
}
