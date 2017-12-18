package ufpi.br.ufpimobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;


public class TelaHome extends AppCompatActivity implements View.OnClickListener {


    private ViewHolder mViewHolder = new ViewHolder();
    private Toolbar toolbarlayout;

    /*
        metódo de criação
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_home);

        toolbarlayout = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbarlayout);


        //recupera as informações da image view

        mViewHolder.imageButton_noticia = findViewById(R.id.im_noticias);
        mViewHolder.imageButton_noticia.setOnClickListener(this);

        mViewHolder.imageButton_calendario = findViewById(R.id.im_calendario);
        mViewHolder.imageButton_calendario.setEnabled(false);
        mViewHolder.imageButton_calendario.setClickable(false);
        mViewHolder.imageButton_calendario.setAlpha(0.5f);
        //mViewHolder.imageButton_calendario.setOnClickListener(this);

        mViewHolder.imageButton_contato = findViewById(R.id.im_contatos);
        mViewHolder.imageButton_contato.setEnabled(false);
        mViewHolder.imageButton_contato.setClickable(false);
        mViewHolder.imageButton_contato.setAlpha(0.5f);
        //mViewHolder.imageButton_contato.setOnClickListener(this);

        mViewHolder.imageButton_radio = findViewById(R.id.im_radio);
        mViewHolder.imageButton_radio.setEnabled(false);
        mViewHolder.imageButton_radio.setClickable(false);
        mViewHolder.imageButton_radio.setAlpha(0.5f);
        //mViewHolder.imageButton_radio.setOnClickListener(this);

        mViewHolder.imageButton_restaurante = findViewById(R.id.im_restaurante);
        mViewHolder.imageButton_restaurante.setEnabled(false);
        mViewHolder.imageButton_restaurante.setClickable(false);
        mViewHolder.imageButton_restaurante.setAlpha(0.5f);
        //mViewHolder.imageButton_restaurante.setOnClickListener(this);

        mViewHolder.imageButton_sobre = findViewById(R.id.im_sobre);
        mViewHolder.imageButton_sobre.setEnabled(false);
        mViewHolder.imageButton_sobre.setClickable(false);
        mViewHolder.imageButton_sobre.setAlpha(0.5f);
        //mViewHolder.imageButton_sobre.setOnClickListener(this);

        mViewHolder.imageButton_evento = findViewById(R.id.im_evento);
        mViewHolder.imageButton_evento.setEnabled(false);
        mViewHolder.imageButton_evento.setClickable(false);
        mViewHolder.imageButton_evento.setAlpha(0.5f);
        //mViewHolder.imageButton_evento.setOnClickListener(this);

        mViewHolder.imageButton_mapa = findViewById(R.id.im_mapa);
        //mViewHolder.imageButton_mapa.setOnClickListener(this);

        mViewHolder.imageButton_sigaa = findViewById(R.id.im_sigaa);
        mViewHolder.imageButton_sigaa.setEnabled(false);
        mViewHolder.imageButton_sigaa.setClickable(false);
        mViewHolder.imageButton_sigaa.setAlpha(0.5f);
        //mViewHolder.imageButton_sigaa.setOnClickListener(this);
    }
    /*
        metodo que implementa a açao do click do botao
    */

    @Override
    public void onClick(View view) {

        int id = view.getId();


        switch (id) {
            case R.id.im_noticias:
                Intent intent = new Intent(getApplicationContext(), MostrarNoticias.class);

                startActivity(intent);

                System.out.println("Hello world");
                break;
            case R.id.im_mapa:

                System.out.println("Ainda ta faltando integrar");
                break;

            case R.id.im_contatos:

                break;

            default:
                Intent intentErro = new Intent(getApplicationContext(), ErroActivity.class);

                startActivity(intentErro);
        }


    }

    /*
        classe que agrupa os elementos da activity e é instanciada lá em cima
     */
    private static class ViewHolder {

        ImageButton imageButton_contato;
        ImageButton imageButton_calendario;
        ImageButton imageButton_sigaa;
        ImageButton imageButton_restaurante;
        ImageButton imageButton_sobre;
        ImageButton imageButton_radio;
        ImageButton imageButton_noticia;
        ImageButton imageButton_evento;
        ImageButton imageButton_mapa;

    }
}
