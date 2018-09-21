package com.arbresystems.appoint.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.arbresystems.appoint.model.Estabelecimento;
import com.arbresystems.appoint.view.PerfilAdmActivity;

import java.util.List;

public class AdapterItemPesquisaEstabelecimento extends BaseAdapter{
    private LayoutInflater mInflater;
    private Context context;
    private List<Estabelecimento> estabelecimentos;

    public AdapterItemPesquisaEstabelecimento(Context context, List<Estabelecimento> estabelecimentos) {
        this.context = context;
        this.estabelecimentos = estabelecimentos;
        mInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return estabelecimentos.size();
    }

    @Override
    public Object getItem(int position) {
        return estabelecimentos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(estabelecimentos.get(position).getIdEstabelecimento());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mInflater.inflate(android.R.layout.simple_list_item_1 ,null);
        TextView nomeEstabelecimento = view.findViewById(android.R.id.text1);

        nomeEstabelecimento.setText(estabelecimentos.get(position).getNome());

        nomeEstabelecimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, PerfilAdmActivity.class));
            }
        });

        return view;
    }
}
