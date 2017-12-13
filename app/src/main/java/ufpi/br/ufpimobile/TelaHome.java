package ufpi.br.ufpimobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class TelaHome extends AppCompatActivity implements View.OnClickListener{


    private ViewHolder mViewHolder = new ViewHolder();

    /*
        metódo de criação
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_home);
        //recupera as informações da image view

        this.mViewHolder.imageButton_noticia = findViewById(R.id.im_noticias);

        this.mViewHolder.imageButton_noticia.setOnClickListener(this);

        this.mViewHolder.imageButton_calendario = findViewById(R.id.im_calendario);

        this.mViewHolder.imageButton_calendario.setOnClickListener(this);

        this.mViewHolder.imageButton_contato = findViewById(R.id.im_contatos);

        this.mViewHolder.imageButton_contato.setOnClickListener(this);

        this.mViewHolder.imageButton_radio = findViewById(R.id.im_radio);

        this.mViewHolder.imageButton_radio.setOnClickListener(this);

        this.mViewHolder.imageButton_restaurante = findViewById(R.id.im_restaurante);

        this.mViewHolder.imageButton_restaurante.setOnClickListener(this);

        this.mViewHolder.imageButton_sobre = findViewById(R.id.im_sobre);

        this.mViewHolder.imageButton_sobre.setOnClickListener(this);

        this.mViewHolder.imageButton_evento = findViewById(R.id.im_evento);

        this.mViewHolder.imageButton_evento.setOnClickListener(this);

        this.mViewHolder.imageButton_mapa = findViewById(R.id.im_mapa);

        this.mViewHolder.imageButton_mapa.setOnClickListener(this);

        this.mViewHolder.imageButton_sigaa = findViewById(R.id.im_sigaa);

        this.mViewHolder.imageButton_sigaa.setOnClickListener(this);

    }
    /*
        metodo que implementa a açao do click do botao
    */

    @Override
    public void onClick(View view) {

        int id  = view.getId();
        if(id == R.id.im_noticias){

            System.out.println("Hello world");


        }
        else{
            // passa o contexto da app  e a classe que vai ser redirecionado o fluxo
            Intent intent = new Intent(getApplicationContext(),ErroActivity.class);

            startActivity(intent);
        }

    }

    /*
        classe que agrupa os elementos da activity e é instanciada lá em cima
     */
    private static class ViewHolder{

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
