package com.arbresystems.appoint.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.arbresystems.appoint.R;
import com.arbresystems.appoint.model.Estabelecimento;
import com.arbresystems.appoint.view.PerfilAdmActivity;
import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterItemPesquisaEstabelecimento extends RecyclerView.Adapter<AdapterItemPesquisaEstabelecimento.ViewHolder> {
    private LayoutInflater mInflater;
    private Context context;
    private List<Estabelecimento> estabelecimentos;

    public AdapterItemPesquisaEstabelecimento(Context context, List<Estabelecimento> estabelecimentos) {
        this.context = context;
        this.estabelecimentos = estabelecimentos;
        mInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterItemPesquisaEstabelecimento.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_pesquisa, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterItemPesquisaEstabelecimento.ViewHolder viewHolder, int i) {
        Estabelecimento estabelecimento = estabelecimentos.get(i);
        viewHolder.txtNome.setText(estabelecimento.getNome());
        //viewHolder.btnDistancia.setText(estabelecimento.getDistancia());
        viewHolder.rtAvaliacao.setRating(estabelecimento.getAvaliacao());
        Glide.with(context).load("http://goo.gl/gEgYUd").into(viewHolder.imgPerfil);
    }

    @Override
    public int getItemCount() {
        return estabelecimentos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtNome;
        public Button btnDistancia;
        public ImageView imgPerfil;
        public RatingBar rtAvaliacao;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNome = itemView.findViewById(R.id.txtNome);
            imgPerfil = itemView.findViewById(R.id.imgPerfil);
            btnDistancia = itemView.findViewById(R.id.btnDistancia);
            rtAvaliacao = itemView.findViewById(R.id.rtAvaliacao);
        }
    }
}
