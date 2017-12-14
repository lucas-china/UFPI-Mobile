package ufpi.br.ufpimobile.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ufpi.br.ufpimobile.R;

/**
 * Classe que retorna um fragmento para a main activity contendo a view Sobre
 */

public class AboutFragment extends android.support.v4.app.Fragment {

    /**
     * Botao colaboradores
     */
    private Button colaboradoresButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View aboutView = inflater.inflate(R.layout.fragment_about, container, false);
        colaboradoresButton = (Button) aboutView.findViewById(R.id.buttonColaboradores);
        colaboradoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CollaboratorsActivity.class);
                startActivity(intent);
            }
        });
        return aboutView;
    }
}