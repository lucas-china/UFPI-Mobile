package ufpi.br.ufpimobile;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TelaAbertura extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_abertura);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),TelaHome2.class);
                ActivityOptionsCompat activityOptionsCompat= ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),R.anim.fadein,R.anim.fadeout);
                ActivityCompat.startActivity(TelaAbertura.this,intent,activityOptionsCompat.toBundle());

                finish();

            }
        },1000);
    }
}
