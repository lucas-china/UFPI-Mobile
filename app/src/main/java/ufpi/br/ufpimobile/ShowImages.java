package ufpi.br.ufpimobile;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ShowImages extends AppCompatActivity {

    private ImageView sw_img;
    private Bitmap bitmap;
    private Button ant, prox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_images);
        sw_img = (ImageView) findViewById(R.id.imgsw);
        ant = (Button) findViewById(R.id.Anterior);
        prox = (Button) findViewById(R.id.Proximo);
        Picasso.with(this).load("http://ufpi.br/images/arquivos_download/Noticias/profa_carminha.jpg").into(sw_img);


        prox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Picasso.with(ShowImages.this).load("http://ufpi.br/images/arquivos_download/Noticias/profa_adriana.jpg").into(sw_img);

                //sw_img.setImageURI();
            }
        });

        ant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sw_img.setImageURI();
            }
        });
    }
}

