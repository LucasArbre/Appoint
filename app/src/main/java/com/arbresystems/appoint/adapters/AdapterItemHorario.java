package com.arbresystems.appoint.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arbresystems.appoint.R;
import com.arbresystems.appoint.viewModels.SectionDataModelHorario;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AdapterItemHorario extends  RecyclerView.Adapter<AdapterItemHorario.ItemRowHolder>{
    private ArrayList<SectionDataModelHorario> horarios;
    private Context mContext;

    public AdapterItemHorario(Context context, ArrayList<SectionDataModelHorario> dataList) {
        this.horarios = dataList;
        this.mContext = context;
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_compromissos_diarios, null);
        ItemRowHolder mh = new ItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(ItemRowHolder itemRowHolder, int i) {
        SimpleDateFormat formatoDia = new SimpleDateFormat("E");
        SimpleDateFormat formatoDiaMes = new SimpleDateFormat("d");

        final String sectionDia = formatoDia.format(horarios.get(i).getDia());
        final String sectionMes = formatoDiaMes.format(horarios.get(i).getDiaMes());

        ArrayList singleSectionItems = horarios.get(i).getAllItemsInSection();



        itemRowHolder.itemMes.setText(sectionMes);
        itemRowHolder.itemDia.setText(sectionDia);

        AdapterItemHorarios itemListDataAdapter = new AdapterItemHorarios(mContext, singleSectionItems);

        itemRowHolder.recycler_view_list.setHasFixedSize(true);
        itemRowHolder.recycler_view_list.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        itemRowHolder.recycler_view_list.setAdapter(itemListDataAdapter);
    }

    @Override
    public int getItemCount() {
        return (null != horarios ? horarios.size() : 0);
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView itemDia;
        protected TextView itemMes;

        protected RecyclerView recycler_view_list;



        public ItemRowHolder(View view) {
            super(view);

            this.itemMes = view.findViewById(R.id.txtMes);
            this.itemDia = view.findViewById(R.id.txtDia);
            this.recycler_view_list = view.findViewById(R.id.recycler_view_compromissos);
        }

    }
}
