package com.curso.lanche.adpter;

import android.content.Context;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.curso.lanche.config.FormatterValor;
import com.curso.lanche.databinding.RecyclerAdpterBinding;
import com.curso.lanche.model.DadosLanches;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<DadosLanches> list;
    private Context context;
    private String qualActivityUsa;

    public Adapter(List<DadosLanches> list,Context context,String qualActivityUsa) {
        this.context = context;
        this.qualActivityUsa = qualActivityUsa;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerAdpterBinding binding = RecyclerAdpterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if (qualActivityUsa.equalsIgnoreCase("PedidoFinalizadoActivity"))
        {
            holder.binding.btnremoveLanche.setVisibility(View.INVISIBLE);
            holder.binding.totalLanche.setVisibility(View.INVISIBLE);
        }
        DadosLanches dados = list.get(position);
        holder.binding.nomeLanche.setText(dados.getNomeLanche());
        holder.binding.totalLanche.setText(String.valueOf(dados.getQuanti_lanche()));
        holder.binding.descricaoProduto.setText(dados.getDescricao());
        holder.binding.valorLanche.setText("R$: "+ FormatterValor.format(dados.getValor()));
        int imageResource = context.getResources().getIdentifier(
                dados.getFtLanche(),
                "drawable",
                context.getPackageName()
        );
        holder.binding.imgLanche.setImageResource(imageResource);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private RecyclerAdpterBinding binding;
        public MyViewHolder(RecyclerAdpterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
