package com.arbresystems.appoint.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.arbresystems.appoint.model.Estabelecimento;

import java.util.List;

public class AdapterItemPromocoes extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context context;
    private List<Estabelecimento> estabelecimentos;

    public AdapterItemPromocoes(Context context, List<Estabelecimento> estabelecimentos) {
        this.context = context;
        this.estabelecimentos = estabelecimentos;
        mInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //View view = mInflater.inflate(android.R.layout.,null);
        return null;
    }
}
