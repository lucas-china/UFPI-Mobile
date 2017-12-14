package ufpi.br.ufpimobile.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ufpi.br.ufpimobile.R;
import ufpi.br.ufpimobile.model.ApplicationObject;
import ufpi.br.ufpimobile.model.GeoPointsDatabase;
import ufpi.br.ufpimobile.system.AsyncTaskTraceFlyingRoute;

import java.util.ArrayList;

/**
 * Created by HugoPiauilino on 14/05/15.
 * Classe que cria o Fragment da View Tracar Rotas
 */
public class TraceRouteFragment extends android.support.v4.app.Fragment {

    /**
     * View do Trace Route Fragment
     */
    private View traceRouteView;


    /**
     * Button que dispara a acao de tracar uma rota entre dois pontos escolhidos
     */
    private Button traceRouteButton;
    private AutoCompleteTextView originEditText;
    private AutoCompleteTextView destinationEditText;
    private String[] search;
    private GeoPointsDatabase bancoDeDados;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        traceRouteView = inflater.inflate(R.layout.fragment_trace_route, container, false);

        originEditText = (AutoCompleteTextView) traceRouteView.findViewById(R.id.originEditText);
        originEditText.setThreshold(1);
        destinationEditText = (AutoCompleteTextView) traceRouteView.findViewById(R.id.destinationEditText);
        destinationEditText.setThreshold(1);
        bancoDeDados = ((Mapas) getActivity()).getGeoPointsDatabase();

        return traceRouteView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayList<String> descricoes = bancoDeDados.getNodesNames();
        search = new String[descricoes.size()];
        search = descricoes.toArray(search);

        testarGeopoints();
    }

    public void testarGeopoints() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_1, search);

        originEditText.setAdapter(adapter);
        destinationEditText.setAdapter(adapter);

        traceRouteButton = (Button) traceRouteView.findViewById(R.id.trace_route_button);
        traceRouteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String origin = originEditText.getText().toString();
                String destination = destinationEditText.getText().toString();
                if (origin.equals("") == true) {
                    Toast.makeText(getActivity().getApplicationContext(), "Ponto de Origem não Informado!", Toast.LENGTH_SHORT).show();
                } else if (destination.equals("") == true) {
                    Toast.makeText(getActivity().getApplicationContext(), "Ponto de Destino não Informado!", Toast.LENGTH_SHORT).show();
                } else if (!((Mapas) getActivity()).getGeoPointsDatabase().hasNode(origin)) {
                    Toast.makeText(getActivity().getApplicationContext(), "Ponto de Origem não Cadastrado!", Toast.LENGTH_SHORT).show();
                } else if (!((Mapas) getActivity()).getGeoPointsDatabase().hasNode(destination)) {
                    Toast.makeText(getActivity().getApplicationContext(), "Ponto de Destino não Cadastrado!", Toast.LENGTH_SHORT).show();
                } else if (origin.equals(destination)) {
                    Toast.makeText(getActivity().getApplicationContext(), "Ponto de Origem é igual ao Ponto de Destino!", Toast.LENGTH_LONG).show();
                } else {
                    InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getBaseContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    int tipoDeMapa = ((ApplicationObject) getActivity().getApplication()).mapa.getTipoDeMapa(); //Tipo de mapa que esta selecionado no momento
                    ((Mapas) getActivity()).onNavigationDrawerItemSelected(tipoDeMapa);
                    //Reta simples interligando os pontos
                    AsyncTaskTraceFlyingRoute tracarRotaAPe = new AsyncTaskTraceFlyingRoute(((Mapas) getActivity()).getGeoPointsDatabase(), ((ApplicationObject) getActivity().getApplicationContext()).mapa);
                    //A linha abaixo utiliza a rota traçada pelo algoritmo de Dijkstra
                    //AsyncTaskTraceWalkingRoute tracarRotaAPe = new AsyncTaskTraceWalkingRoute(((Mapas) getActivity()).getGeoPointsDatabase(), ((ApplicationObject) getActivity().getApplicationContext()).mapa, ((Mapas) getActivity()).getGrafo());
                    //A linha abaixo utiliza a rota traçada pelo Google
                    //AsyncTaskTraceRoute tracarRota = new AsyncTaskTraceRoute(((Mapas) getActivity()).getGeoPointsDatabase(), ((ApplicationObject) getActivity().getApplicationContext()).mapa);
                    tracarRotaAPe.execute(origin, destination);
                }
            }
        });

        destinationEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    traceRouteButton.callOnClick();
                }
                return false;
            }
        });
    }
}