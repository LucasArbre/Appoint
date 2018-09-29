package com.arbresystems.appoint.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.arbresystems.appoint.model.Estabelecimento;

import java.util.ArrayList;
import java.util.List;

public class AdapterItemPromocoes extends ArrayAdapter<String> {
    public static final String tag = "ListaPomocoes";
    private Context context;
    int resource;

    public AdapterItemPromocoes(Context context, int resource, ArrayList<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //metodos pra pegar os dados e coolocar na view
        //ex String titulouau = getItem(position).getTitulo();
        //Promocoes promos = new Promocoes(Titulo....);

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        //altera lá no layout:
        // ex>  TextView titulo = convertView.findViewById(R.id.txtTitulo);
        //titulo.setText(titulouau);
        return convertView;
    }
}
