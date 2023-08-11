package com.curso.lanche.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.curso.lanche.R;
import com.curso.lanche.adpter.Adapter;
import com.curso.lanche.config.FormatterValor;
import com.curso.lanche.config.RecyclerItemClick;
import com.curso.lanche.databinding.LanchesBinding;
import com.curso.lanche.model.DadosLanches;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private double valorTotal = 0;
    private LanchesBinding binding;
    private Adapter adapter;
    private List<DadosLanches> lista;
    private List<DadosLanches> lanchesClicados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LanchesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FormatterValor.format(valorTotal);
        lista = new ArrayList<>();
        lanchesClicados = new ArrayList<>();
        onclickRecycler();

        if (valorTotal == 0){
            binding.btnFinalizarCompra.setEnabled(false);
        }


        binding.btnFinalizarCompra.setOnClickListener(view -> {

        Intent i = new Intent(this,PedidoFinalizadoActivity.class);
        i.putParcelableArrayListExtra("listaLanches", (ArrayList<? extends Parcelable>) lanchesClicados);
        i.putExtra("ValorTotal",valorTotal);
        startActivity(i);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        configRecycler();
    }

    public void onclickRecycler()
    {
        binding.recycler.addOnItemTouchListener(new RecyclerItemClick(getApplicationContext(), binding.recycler,
                new RecyclerItemClick.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        DadosLanches dadosLanches = lista.get(position);
                        valorTotal += dadosLanches.getValor();
                        binding.txtValorCompra.setText(FormatterValor.format(valorTotal));
                        lanchesClicados.add(dadosLanches);
                        if (valorTotal != 0) binding.btnFinalizarCompra.setEnabled(true);
                        else binding.btnFinalizarCompra.setEnabled(false);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {


                    }
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }

                }));
    }
    private void configRecycler()
    {
        addLanches();
        RecyclerView.LayoutManager myLayoutManager = new LinearLayoutManager( getApplicationContext());
        binding.recycler.setLayoutManager( myLayoutManager );
        binding.recycler.setHasFixedSize( true );
        adapter = new Adapter(lista,getApplicationContext(),"");
        binding.recycler.setAdapter(adapter);
    }
    private void addLanches()
    {
        String descricao;
        descricao = getString(R.string.xTudo);
        DadosLanches dados1 = new DadosLanches("X-tudo",descricao, "xtudo", 13.50);
        lista.add(dados1);


        descricao = getString(R.string.calafrango);
        DadosLanches dados2 = new DadosLanches("calafrango", descricao, "calafrango", 17.50);
        lista.add(dados2);


        descricao = getString(R.string.xCalabresa);
        DadosLanches dados3 = new DadosLanches("xCalabresa", descricao, "x_calabresa", 15.99);
        lista.add(dados3);


        descricao = getString(R.string.xBacon);
        DadosLanches dados4 = new DadosLanches("xBacon", descricao, "xbacon", 15.00);
        lista.add(dados4);


        descricao = getString(R.string.xSalada);
        DadosLanches dados5 = new DadosLanches("xSalada", descricao, "x_salada", 14.00);
        lista.add(dados5);


        descricao = getString(R.string.dogaoSimples);
        DadosLanches dados6 = new DadosLanches("dogaoSimples", descricao, "dogao_simples", 12.00);
        lista.add(dados6);

        descricao = getString(R.string.dogaoEspecial);
        DadosLanches dados7 = new DadosLanches("dogaoEspecial", descricao, "dogao_especial", 16.00);
        lista.add(dados7);

    }
}