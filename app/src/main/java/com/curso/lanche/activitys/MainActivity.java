package com.curso.lanche.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.curso.lanche.R;
import com.curso.lanche.adpter.Adapter;
import com.curso.lanche.databinding.LanchesBinding;
import com.curso.lanche.model.DadosLanches;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private double valorFinal = 0;
    private String convert,convetNmr;
    int x_tudo = 0,bacon = 0,dogaoSimples = 0, dogaoEspecial = 0, calabresa = 0, salada = 0, calafango = 0;
    private Button bntFinalizar;
    private String format = "00.00";
    private NumberFormat formatter = new DecimalFormat(format);
    String formatValoFinal;
    private LanchesBinding binding;
    private Adapter adapter;
    private List<DadosLanches> lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LanchesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        convert = String.valueOf(valorFinal);
        lista = new ArrayList<>();

        if (valorFinal == 0){
            binding.btnFinalizarCompra.setEnabled(false);
        }


        binding.btnFinalizarCompra.setOnClickListener(view -> {

        Intent i = new Intent(getApplicationContext(),PedidoFinalizadoActivity.class);
        i.putExtra("valorTotal",valorFinal);
        startActivity(i);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        configRecycler();
    }

   /* public void onClick(View view){


        switch (view.getId()){
            case R.id.imagemXBacon:
                bacon += 1;
                totalPedidos();
                valorFinal += 16.00;
                convert = String.valueOf(valorFinal);
                formatValoFinal = formatter.format(valorFinal);
                valorCompra.setText("R$ "+formatValoFinal);
                break;
            case R.id.dogaoSimples:
                dogaoSimples += 1;
                totalPedidos();
                valorFinal += 10.00;
                formatValoFinal = formatter.format(valorFinal);
                valorCompra.setText("R$ "+formatValoFinal);
                break;
            case R.id.imgLanche:
                dogaoEspecial += 1;
                totalPedidos();
                valorFinal += 13.50;
                formatValoFinal = formatter.format(valorFinal);
                valorCompra.setText("R$ "+formatValoFinal);
                break;
            case R.id.imagemCalabresa:
                calabresa += 1;
                totalPedidos();
                valorFinal += 15.00;
                formatValoFinal = formatter.format(valorFinal);
                valorCompra.setText("R$ "+formatValoFinal);
                break;
            case R.id.x_salada:
                salada += 1;
                totalPedidos();
                valorFinal += 14.00;
                formatValoFinal = formatter.format(valorFinal);
                valorCompra.setText("R$ "+formatValoFinal);
                break;
            case R.id.x_tudo:
                x_tudo += 1;
                totalPedidos();
                valorFinal += 17.50;
                formatValoFinal = formatter.format(valorFinal);
                valorCompra.setText("R$ "+formatValoFinal);
                break;
            case R.id.calafrango:
                calafango += 1;
                totalPedidos();
                valorFinal += 17.50;
                formatValoFinal = formatter.format(valorFinal);
                valorCompra.setText("R$ "+formatValoFinal);
                break;
        }
        bntFinalizar.setEnabled(true);
    }*/

   /* public void removeNmrLanche(View view){

        switch (view.getId()){
            case R.id.btnRemoveBacon:
                bacon -= 1;
                if (bacon <= -1){
                    bacon = 0;
                    formatValoFinal = formatter.format(valorFinal);
                    valorCompra.setText("R$ "+formatValoFinal);
                }else{
                    totalPedidos();
                    valorFinal -= 16.00;
                    formatValoFinal = formatter.format(valorFinal);
                    valorCompra.setText("R$ "+formatValoFinal);
                }

                break;
            case R.id.btnRemoveDogaoSimples:
                dogaoSimples -= 1;
                if (dogaoSimples == -1){
                    dogaoSimples = 0;
                    formatValoFinal = formatter.format(valorFinal);
                    valorCompra.setText("R$ "+formatValoFinal);
                }else{
                    totalPedidos();
                    valorFinal -= 10.00;
                    formatValoFinal = formatter.format(valorFinal);
                    valorCompra.setText("R$ "+formatValoFinal);
                }

                break;
            case R.id.btnremoveDogaoEspecial:
                dogaoEspecial -= 1;
                if (dogaoEspecial == -1){
                    dogaoEspecial = 0;
                    formatValoFinal = formatter.format(valorFinal);
                    valorCompra.setText("R$ "+formatValoFinal);
                }else{
                    totalPedidos();
                    valorFinal -= 13.50;
                    formatValoFinal = formatter.format(valorFinal);
                    valorCompra.setText("R$ "+formatValoFinal);
                }

                break;
            case R.id.btnRemoveCalabresa:
                calabresa -= 1;
                if (calabresa == -1){
                    calabresa = 0;
                    formatValoFinal = formatter.format(valorFinal);
                    valorCompra.setText("R$ "+formatValoFinal);
                }else{
                    totalPedidos();
                    valorFinal -= 15.00;
                    formatValoFinal = formatter.format(valorFinal);
                    valorCompra.setText("R$ "+formatValoFinal);
                }

                break;
            case R.id.btnRemovesalada:
                salada -= 1;
                if (salada == -1){
                    salada = 0;
                    formatValoFinal = formatter.format(valorFinal);
                    valorCompra.setText("R$ "+formatValoFinal);
                }else{
                    totalPedidos();
                    valorFinal -= 14.00;
                    formatValoFinal = formatter.format(valorFinal);
                    valorCompra.setText("R$ "+formatValoFinal);
                }

                break;
            case R.id.btnRemoveXTudo:
                x_tudo -= 1;
                if (x_tudo == -1){
                    x_tudo = 0;
                    formatValoFinal = formatter.format(valorFinal);
                    valorCompra.setText("R$ "+formatValoFinal);
                }else{
                    totalPedidos();
                    valorFinal -= 17.50;
                    formatValoFinal = formatter.format(valorFinal);
                    valorCompra.setText("R$ "+formatValoFinal);
                }

                break;
            case R.id.btnRemoveCalafrango:
                calafango -= 1;
                if (calafango == -1){
                    calafango = 0;
                    formatValoFinal = formatter.format(valorFinal);
                    valorCompra.setText("R$ "+formatValoFinal);
                }else{
                    totalPedidos();
                    valorFinal -= 17.50;
                    formatValoFinal = formatter.format(valorFinal);
                    valorCompra.setText("R$ "+formatValoFinal);
                }

                break;

        }
        if (valorFinal == 0){
            bntFinalizar.setEnabled(false);
        }


    }*/

    /*public void totalPedidos(){//esse metodo mostra o total de lanche pedido

        convetNmr = String.valueOf(bacon);
        nmrBacon.setText(convetNmr);

        convetNmr = String.valueOf(dogaoSimples);
        nmrDogaoSimples.setText(convetNmr);

        convetNmr = String.valueOf(dogaoEspecial);
        nmrDogaoEspecial.setText(convetNmr);

        convetNmr = String.valueOf(calabresa);
        nmrCalabresa.setText(convetNmr);

        convetNmr = String.valueOf(salada);
        nmrSalada.setText(convetNmr);

        convetNmr = String.valueOf(x_tudo);
        nmrTudo.setText(convetNmr);

        convetNmr = String.valueOf(calafango);
        nmrCalafrango.setText(convetNmr);
    }*/

    private void configRecycler()
    {
        addLanches();
        RecyclerView.LayoutManager myLayoutManager = new LinearLayoutManager( getApplicationContext());
        binding.recycler.setLayoutManager( myLayoutManager );
        binding.recycler.setHasFixedSize( true );
        adapter = new Adapter(lista,getApplicationContext());
        binding.recycler.setAdapter(adapter);
    }
    private void addLanches()
    {
        String descricao;
        descricao = getString(R.string.xTudo);
        DadosLanches dados1 = new DadosLanches("X-tudo",descricao, R.drawable.xtudo, "13,50");
        lista.add(dados1);


        descricao = getString(R.string.calafrango);
        DadosLanches dados2 = new DadosLanches("calafrango", descricao, R.drawable.calafrango, "17,50");
        lista.add(dados2);


        descricao = getString(R.string.xCalabresa);
        DadosLanches dados3 = new DadosLanches("xCalabresa", descricao, R.drawable.x_calabresa, "15,99");
        lista.add(dados3);


        descricao = getString(R.string.xBacon);
        DadosLanches dados4 = new DadosLanches("xBacon", descricao, R.drawable.xbacon, "15,00");
        lista.add(dados4);


        descricao = getString(R.string.xSalada);
        DadosLanches dados5 = new DadosLanches("xSalada", descricao, R.drawable.x_salada, "14,00");
        lista.add(dados5);


        descricao = getString(R.string.dogaoSimples);
        DadosLanches dados6 = new DadosLanches("dogaoSimples", descricao, R.drawable.dogao_simples, "12,00");
        lista.add(dados6);

        descricao = getString(R.string.dogaoEspecial);
        DadosLanches dados7 = new DadosLanches("dogaoEspecial", descricao, R.drawable.dogao_especial, "16,00");
        lista.add(dados7);

    }
}