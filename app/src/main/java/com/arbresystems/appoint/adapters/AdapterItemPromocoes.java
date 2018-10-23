package com.arbresystems.appoint.adapters;

import android.app.LauncherActivity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.arbresystems.appoint.R;
import com.arbresystems.appoint.model.Estabelecimento;
import com.arbresystems.appoint.model.Promocao;

import java.util.ArrayList;
import java.util.List;

public class AdapterItemPromocoes extends RecyclerView.Adapter {

    private List<Promocao> listItems;
    private Context context;

    public AdapterItemPromocoes(List<Promocao> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_promos, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    /*@Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Promocao listItem = listItems.get(i);
        viewHolder.titulo.setText(listItem.getTitulo());
        viewHolder.dscrp.setText(listItem.getDescricao());
        viewHolder.valor.setText(listItem.getPorcentagem());
        //viewHolder.verMais.setOnClickListener();
    }*/

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtValor;
        public TextView txtTitulo;
        public TextView txtDescricao;
        public Button btnVerMais;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtValor = itemView.findViewById(R.id.valor_promo);
            txtTitulo = itemView.findViewById(R.id.txtTitulo);
            txtDescricao = itemView.findViewById(R.id.txtDscrp);
            btnVerMais = itemView.findViewById(R.id.btnVerMais);
        }
    }
}
