package com.arbresystems.appoint.adapters;

import android.annotation.SuppressLint;
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

public class AdapterItemPromocoes extends RecyclerView.Adapter<AdapterItemPromocoes.ViewHolder> {

    private List<Promocao> listItems;
    private Context context;

    public AdapterItemPromocoes(List<Promocao> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterItemPromocoes.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_promos, viewGroup, false);
        return new ViewHolder(v);
    }

@SuppressLint("SetTextI18n")
@Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Promocao listItem = listItems.get(i);
        viewHolder.txtTitulo.setText(listItem.getTitulo());
        viewHolder.txtDescricao.setText(listItem.getDescricao());
        viewHolder.txtValor.setText(listItem.getPorcentagem() + "%");
        viewHolder.btnVerMais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

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
