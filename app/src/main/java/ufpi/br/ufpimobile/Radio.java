package ufpi.br.ufpimobile;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class Radio extends AppCompatActivity {

    private Button botaoPlay;
    private MediaPlayer mediaPlayer;
    private String stream = "http://fm.ufpi.br:8000/admin";
    private boolean pausado = false;
    private boolean iniciado = false;
    private ImageView botao_play_stop;
    private boolean play = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_Radio);
        toolbar.setTitle("Rádio UFPI");
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

        botaoPlay = (Button) findViewById(R.id.botaoPlayId);
        botaoPlay.setEnabled(false);
        botaoPlay.setText("Carregando...");


        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        new PlayerTask().execute(stream);

        botaoPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iniciado){
                    iniciado = false;
                    mediaPlayer.pause();
                    botaoPlay.setText("Play");

                }else{
                    iniciado = true;
                    mediaPlayer.start();
                    botaoPlay.setText("Stop");

                }

            }
        });
    }

    private class PlayerTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {
            try {

                mediaPlayer.setDataSource(strings[0]);
                mediaPlayer.prepare();
                pausado=true;
            } catch (IOException e) {
                e.printStackTrace();
            }


            return pausado;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            botaoPlay.setEnabled(true);
            botaoPlay.setText("Play");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(iniciado){
            mediaPlayer.pause();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(pausado){
            mediaPlayer.release();
        }
    }
}
