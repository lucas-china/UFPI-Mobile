package ufpi.br.ufpimobile.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import ufpi.br.ufpimobile.model.Evento;

/**
 * Created by lucas_brito on 30/12/17.
 */

public class EventoAdapter extends RecyclerView.Adapter{

    private List<Evento> eventos;

    public EventoAdapter(List<Evento> eventos){
        this.eventos = eventos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }
}
