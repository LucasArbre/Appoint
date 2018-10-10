package com.arbresystems.appoint.viewModels;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arbresystems.appoint.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RecyclerViewDataAdapterHorario extends  RecyclerView.Adapter<RecyclerViewDataAdapterHorario.ItemRowHolder>{
    private ArrayList<SectionDataModelHorario> dataList;
    private Context mContext;

    public RecyclerViewDataAdapterHorario(Context context, ArrayList<SectionDataModelHorario> dataList) {
        this.dataList = dataList;
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

        final String sectionDia = formatoDia.format(dataList.get(i).getDia());
        final String sectionMes = formatoDiaMes.format(dataList.get(i).getDiaMes());

        ArrayList singleSectionItems = dataList.get(i).getAllItemsInSection();



        itemRowHolder.itemMes.setText(sectionMes);
        itemRowHolder.itemDia.setText(sectionDia);

        com.arbresystems.appoint.viewModels.SectionListDataAdapterHorario itemListDataAdapter = new com.arbresystems.appoint.viewModels.SectionListDataAdapterHorario(mContext, singleSectionItems);

        itemRowHolder.recycler_view_list.setHasFixedSize(true);
        itemRowHolder.recycler_view_list.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        itemRowHolder.recycler_view_list.setAdapter(itemListDataAdapter);
    }

    @Override
    public int getItemCount() {
        return (null != dataList ? dataList.size() : 0);
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
