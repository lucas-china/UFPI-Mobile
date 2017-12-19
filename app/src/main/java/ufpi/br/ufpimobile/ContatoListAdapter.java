package ufpi.br.ufpimobile;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ufpi.br.ufpimobile.model.Contato;

/**
 * Created by UFPI 236345 on 19/12/2017.
 */

public class ContatoListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Contato> mContatosList;

    //Constructor

    public ContatoListAdapter(Context mContext, List<Contato> mContatosList) {
        this.mContext = mContext;
        this.mContatosList = mContatosList;
    }

    @Override
    public int getCount() {
        return mContatosList.size();
    }

    @Override
    public Object getItem(int position) {
        return mContatosList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(mContext, R.layout.activity_lista_contato, null);
        TextView nomeContato = (TextView) v.findViewById(R.id.nomeContatoId);
        TextView nContato = (TextView) v.findViewById(R.id.nContatoId);
        TextView email = (TextView) v.findViewById(R.id.emailContatoId);
        //Set text for TextView
        nomeContato.setText(mContatosList.get(position).getNome());
        nContato.setText(String.valueOf(mContatosList.get(position).getContato()));
        email.setText(mContatosList.get(position).getEmail());

        //Save product id to tag
        v.setTag(mContatosList.get(position).getId());

        return v;
    }
}
