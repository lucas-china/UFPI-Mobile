package ufpi.br.ufpimobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;


import org.json.JSONException;
import org.json.JSONObject;

import ufpi.br.ufpimobile.adapter.*;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ufpi.br.ufpimobile.model.NoticiaEspecifica;

public class NoticiaEspecificaActivity extends AppCompatActivity {

    public RequestQueue queue;
    private String href;
    private NoticiaEspecifica noticiaEspecifica;
    private ArrayList<String> urls_img;
    private TextView body;
    private TextView title;
    private ImageView img_id;
    private Button prox;
    private LinearLayout layout;
    private Integer it_img;
    private View prox_view;
//    private RecyclerView mRecyclerView;
//    private RecyclerView.Adapter mAdapter;
//    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia_especifica);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();

        urls_img = new ArrayList<String>();
        //href = (String) bd.get ("href");
        int code = (Integer) bd.get("code"); // ou seja, pegar o atributo code na activity anterior, pois o code é a chave que identifica uma notícia específica

        it_img = 0;
        body = (TextView) findViewById(R.id.body);
        title = (TextView) findViewById(R.id.titlene);
        img_id = (ImageView) findViewById(R.id.img_id);
        prox = (Button) findViewById(R.id.Prox);
        prox_view = findViewById(R.id.Prox);
        layout = (LinearLayout) findViewById(R.id.layout);
        //linkAdapter

//        mRecyclerView = (RecyclerView) findViewById(R.id.show_links);
//        mRecyclerView.setHasFixedSize(true);
//        mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);

        //img_id.setImageBitmap(bmp);
        prox_view.setVisibility(View.GONE);
        //title.setText((String) bd.get ("title"));
        //queue = Volley.newRequestQueue(this);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_Noticias_Especifica);
        toolbar.setTitle("Notícias UFPI");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(getApplicationContext(), MostrarNoticias.class);
                finish();
                startActivity(home);
            }
        });



        //String url ="http://192.168.0.110:8000/noticiaespecifica";
        // to -  do
        String url = "http://mobile.ufpi.br/api/articles/" + code;

        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(
                        Request.Method.GET,
                        url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                //String titulo = response.getString("titulo");
                                //String corppo = response.getString("text");
                                Gson gson = new Gson();
                                NoticiaEspecifica not = gson.fromJson(String.valueOf(response),NoticiaEspecifica.class);

                                //title.setText(not.getTitulo());
                                //body.setText(not.getText().toString());
                                noticiaProcess(not);
                                getImages(not);

                            }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            title.setText("Impossível colocar o Titulo");
                            body.setText("Impossível colocar o Corpo");
                        }
                }
                );

        RequestQueue fila = Volley.newRequestQueue(this);
        fila.add(jsonObjectRequest);

        prox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                it_img++;
                Integer pos = it_img%urls_img.size();
                Picasso.with(NoticiaEspecificaActivity.this).load(urls_img.get(pos)).into(img_id);
            }
        });
    }

    void noticiaProcess(NoticiaEspecifica not){
        //System.out.println(noticiaEspecificaList.toString());
        title.setText(not.getTitulo());
        List<String> links_list = new ArrayList<String>();
        String text = "";
        for(String txt: not.getText()){
            text = text + txt + "\n" + "\n";

        }

        body.setText(text);
//        mAdapter = new LinkAdapter(links_list);
//        mRecyclerView.setAdapter(mAdapter);
        body.setClickable(true);
        body.setMovementMethod(LinkMovementMethod.getInstance());
       // body.setText(Html.fromHtml(text));
    }

    void getImages(NoticiaEspecifica not){
        for(Object img: not.getImages()){
            Log.e("MaisNoticias",img.toString());
            urls_img.add(img.toString());
        }
        if(!urls_img.isEmpty()){
            if(urls_img.size() > 1){
                prox_view.setVisibility(View.VISIBLE);
            }
            Picasso.with(NoticiaEspecificaActivity.this).load(urls_img.get(0)).into(img_id);
        }

    }


}




