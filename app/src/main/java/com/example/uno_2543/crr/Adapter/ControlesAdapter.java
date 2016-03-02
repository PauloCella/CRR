package com.example.uno_2543.crr.Adapter;

/**
 * Created by uno-2543 on 26/02/16.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.uno_2543.crr.Models.ControleAplicacao;
import com.example.uno_2543.crr.Models.Movie;
import com.example.uno_2543.crr.R;

import java.util.List;

public class ControlesAdapter extends RecyclerView.Adapter<ControlesAdapter.MyViewHolder> {

    private List<ControleAplicacao> controleList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView descricao, tipo_repelente, duracao;

        public MyViewHolder(View view) {
            super(view);
            descricao = (TextView) view.findViewById(R.id.descricao);
            tipo_repelente = (TextView) view.findViewById(R.id.tipo_repelente);
            duracao = (TextView) view.findViewById(R.id.horario_aplicacao);
        }
    }


    public ControlesAdapter(List<ControleAplicacao> controleList) {
        this.controleList = controleList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.controles, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ControleAplicacao ca = controleList.get(position);
        holder.descricao.setText(ca.getDescricao().toString());
        holder.tipo_repelente.setText(ca.getTipo().toString());
        holder.duracao.setText(ca.getDuracao().toString());
    }

    @Override
    public int getItemCount() {
        return controleList.size();
    }
}