package ufpi.br.ufpimobile.adapter;

/**
 * Created by UFPI 236345 on 13/12/2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ufpi.br.ufpimobile.model.Noticia;
import ufpi.br.ufpimobile.R;

/**
 * Created by jonin on 28/11/2017.
 */

public class NoticiaAdapter extends RecyclerView.Adapter<NoticiaAdapter.MyViewHolder> {

    public List<Noticia> noticiaList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView titulo, data, hora;

        public MyViewHolder(View view) {
            super(view);
            titulo = (TextView) view.findViewById(R.id.titulo);
            data = (TextView) view.findViewById(R.id.data);
            hora = (TextView) view.findViewById(R.id.hora);
        }
    }


    public NoticiaAdapter(List<Noticia> noticiaList) {
        this.noticiaList = noticiaList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_noticia, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Noticia n = noticiaList.get(position);
        holder.titulo.setText(n.getTitulo());
        holder.data.setText(n.get_data());
        holder.hora.setText(n.getHora());
    }

    @Override
    public int getItemCount() {
        return noticiaList.size();
    }
}
