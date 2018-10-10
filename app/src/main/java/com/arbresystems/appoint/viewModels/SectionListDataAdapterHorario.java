package com.arbresystems.appoint.viewModels;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arbresystems.appoint.R;
import com.arbresystems.appoint.model.Horario;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class SectionListDataAdapterHorario extends RecyclerView.Adapter<SectionListDataAdapterHorario.SingleItemRowHolder> {
    private ArrayList<Horario> itemsList;
    private Context mContext;

    public SectionListDataAdapterHorario(Context context, ArrayList<Horario> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_compromisso_item, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {

        Horario singleItem = itemsList.get(i);

        holder.nomeServico.setText(singleItem.getServico());

        SimpleDateFormat formatoHora = new SimpleDateFormat("h:mm a");


        String in = formatoHora.format(singleItem.getHoraInicio());
        String tr = formatoHora.format(singleItem.getHoraTermino());


        String total = String.valueOf(in+" _ "+tr);
        holder.horario.setText(total);
    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected ProgressBar progresso;
        protected TextView nomeServico;
        protected TextView horario;


        public SingleItemRowHolder(View view) {
            super(view);

            this.nomeServico = view.findViewById(R.id.txtCompromisso);
            this.horario = view.findViewById(R.id.txtHorario);
            this.progresso = view.findViewById(R.id.pgProgresso);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Toast.makeText(v.getContext(), nomeServico.getText(), Toast.LENGTH_SHORT).show();

                }
            });

        }

    }

}
