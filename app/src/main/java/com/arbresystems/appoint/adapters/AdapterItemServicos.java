package com.arbresystems.appoint.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arbresystems.appoint.R;
import com.arbresystems.appoint.model.Servico;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdapterItemServicos extends RecyclerView.Adapter<AdapterItemServicos.ItemRowHolder>{
    private ArrayList<Servico> dataList;
    private Context mContext;

    public AdapterItemServicos(Context context, ArrayList<Servico> dataList) {
        this.dataList = dataList;
        this.mContext = context;
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_servico, null);
        ItemRowHolder mh = new ItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(ItemRowHolder itemRowHolder, int i) {

        final String name = dataList.get(i).getNome();
        final String duracao = dataList.get(i).getDuracao();
        final String preco = dataList.get(i).getPreco();

        itemRowHolder.itemNome.setText(name);
        itemRowHolder.itemPreco.setText(preco);
        itemRowHolder.itemDuracao.setText(duracao);
    }

    @Override
    public int getItemCount() {
        return (null != dataList ? dataList.size() : 0);
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView itemNome;
        protected TextView itemDuracao;
        protected TextView itemPreco;



        public ItemRowHolder(View view) {
            super(view);

            this.itemNome =  view.findViewById(R.id.txtNomeServico);
            this.itemDuracao = view.findViewById(R.id.txtDuracao);
            this.itemPreco = view.findViewById(R.id.txtPrecoServico);

        }

    }

    public void updateList(ArrayList<Servico> novaLista){
        dataList = new ArrayList<>();
        dataList.addAll(novaLista);
        notifyDataSetChanged();
    }
}
