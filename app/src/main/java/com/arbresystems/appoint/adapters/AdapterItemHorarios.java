package com.arbresystems.appoint.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arbresystems.appoint.R;
import com.arbresystems.appoint.model.Atendimento;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class AdapterItemHorarios extends RecyclerView.Adapter<AdapterItemHorarios.SingleItemRowHolder> {
    private ArrayList<Atendimento> atendimentos;
    private Context mContext;

    public AdapterItemHorarios(Context context, ArrayList<Atendimento> atendimentos) {
        this.atendimentos = atendimentos;
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

        Atendimento atendimento = atendimentos.get(i);

        //holder.nomeServico.setText(atendimento.getDescricao());
        holder.nomeServico.setText(atendimento.getNome());

        SimpleDateFormat formatoHora = new SimpleDateFormat("h:mm a");


        String in = formatoHora.format(atendimento.getDataHorarioInicio());
        String tr = formatoHora.format(atendimento.getDataHorarioFim());


        String total = String.valueOf(in+" _ "+tr);
        holder.horario.setText(total);
    }

    @Override
    public int getItemCount() {
        return (null != atendimentos ? atendimentos.size() : 0);
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
