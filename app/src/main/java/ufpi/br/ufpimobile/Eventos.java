package ufpi.br.ufpimobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

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

import ufpi.br.ufpimobile.adapter.EventoAdapter;
import ufpi.br.ufpimobile.adapter.NoticiaAdapter;
import ufpi.br.ufpimobile.controllers.TestConnection;
import ufpi.br.ufpimobile.model.Evento;
import ufpi.br.ufpimobile.model.Noticia;

public class Eventos extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RequestQueue queue;
    private List<Evento> listaEvents;
    private RecyclerView.Adapter mAdapter;
    String url = "http://mobile.ufpi.br/api/notifications/5a43f41c5446bc001445e4af";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_Eventos);
        toolbar.setTitle("Eventos UFPI");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(getApplicationContext(), TelaHome2.class);
                finish();
                startActivity(home);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.listaEventos);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);

        if (new TestConnection(getApplicationContext()).isConnected()) {
            queue = Volley.newRequestQueue(this);


            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
//                        noticia.setText("Response is: "+ response.toString());
                            Type listType = new TypeToken<ArrayList<Evento>>() {
                            }.getType();
                            listaEvents = new Gson().fromJson(response, listType);
                            if(listaEvents != null){
                                mAdapter = new EventoAdapter(listaEvents, getApplicationContext());
                                recyclerView.setAdapter(mAdapter);
                            }
                            else {
                                Toast toast = Toast.makeText(getApplicationContext(), "Nenhum evento cadastrado!!", Toast.LENGTH_LONG);
                                toast.show();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
//                noticia.setText("That didn't work!");
                    System.out.println("Deu merda no volley do Eventos");
                }
            });
            queue.add(stringRequest);
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "Sem acesso a Internet!!", Toast.LENGTH_LONG);
            toast.show();
        }

    }


}
