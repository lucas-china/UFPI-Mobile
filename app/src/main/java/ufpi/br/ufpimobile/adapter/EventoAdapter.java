package ufpi.br.ufpimobile.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ufpi.br.ufpimobile.R;
import ufpi.br.ufpimobile.model.Evento;

/**
 * Created by lucas_brito on 30/12/17.
 */

public class EventoAdapter extends RecyclerView.Adapter{

    private List<Evento> eventos;
    private Context context;


    public class EventoViewHolder extends RecyclerView.ViewHolder {
        TextView nomeEvento;
        TextView local;
        TextView data;
        TextView hora;

        public EventoViewHolder(View itemView) {
            super(itemView);
            nomeEvento = (TextView) itemView.findViewById(R.id.titulo);
            local = (TextView) itemView.findViewById(R.id.local);
            data = (TextView) itemView.findViewById(R.id.data);
            hora = (TextView) itemView.findViewById(R.id.horario);
        }
    }

    public EventoAdapter(List<Evento> eventos, Context context){
        this.eventos = eventos;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.activity_evento, parent, false);
        EventoViewHolder holder = new EventoViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewholder, int position) {

        EventoViewHolder holder = (EventoViewHolder) viewholder;

        Evento evento = eventos.get(position);

        holder.nomeEvento.setText(evento.getTitle());
        holder.local.setText(evento.getLocation());
        holder.data.setText(evento.getData());
        holder.hora.setText(evento.getHora());


    }

    @Override
    public int getItemCount() {

        return eventos.size();
    }
}
