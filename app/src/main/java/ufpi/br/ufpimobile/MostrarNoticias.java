package ufpi.br.ufpimobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ufpi.br.ufpimobile.adapter.ClickListener;
import ufpi.br.ufpimobile.adapter.NoticiaAdapter;
import ufpi.br.ufpimobile.adapter.RecyclerTouchListener;
import ufpi.br.ufpimobile.model.Noticia;

/**
 * Created by UFPI 236345 on 13/12/2017.
 */

public class MostrarNoticias extends AppCompatActivity {
    public RequestQueue queue;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Noticia> noticiaList;
    private ProgressBar progressBar;
    private TextView noticia;

    /**
     * classe que implementa a conexão com o server
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_Noticias);
        toolbar.setTitle("Noticias UFPI");
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

        noticia = (TextView) findViewById(R.id.show);


        //final TextView noticia = (TextView) findViewById(R.id.show);
//        List<Noticia> noticiaList = new ArrayList<Noticia>();
        mRecyclerView = (RecyclerView) findViewById(R.id.show_noticia);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        queue = Volley.newRequestQueue(this);

        //String url = "https://ufpi-mobile-cm.herokuapp.com/api/articles";

        String url = "http://10.56.14.220:8000/noticias";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
//                        noticia.setText("Response is: "+ response.toString());
                        Type listType = new TypeToken<ArrayList<Noticia>>() {
                        }.getType();
                        noticiaList = new Gson().fromJson(response.toString(), listType);
//                                noticia.setText(noticiaList .toString());
                        mAdapter = new NoticiaAdapter(noticiaList);
                        mRecyclerView.setAdapter(mAdapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                noticia.setText("That didn't work!");
            }
        });
        queue.add(stringRequest);

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {

                String href = noticiaList.get(position).getHref();
                String title = noticiaList.get(position).getTitulo();
                Intent troca = new Intent(MostrarNoticias.this, NoticiaEspecificaActivity.class);
                troca.putExtra("href", href);
                troca.putExtra("title", title);
                MostrarNoticias.this.startActivity(troca);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


    }
}
