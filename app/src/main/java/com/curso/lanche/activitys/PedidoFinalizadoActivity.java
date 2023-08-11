package com.curso.lanche.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.curso.lanche.R;
import com.curso.lanche.adpter.Adapter;
import com.curso.lanche.config.FormatterValor;
import com.curso.lanche.databinding.ActivityPedidoFinalizadoBinding;
import com.curso.lanche.model.Cep;
import com.curso.lanche.model.DadosLanches;
import com.curso.lanche.service.CepServices;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PedidoFinalizadoActivity extends AppCompatActivity {
    private int clickDouble = 0,progresso = 1;
    private double valorFinal = 4.00, valorTotalRecuperado;
    private Retrofit retrofit;
    private ActivityPedidoFinalizadoBinding binding;
    private List<DadosLanches> lanchesRecupe;
    private int doubleClick= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  ActivityPedidoFinalizadoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        lanchesRecupe = new ArrayList<>();
        recuperarDados();
        binding.progressoPagamento.setVisibility(View.VISIBLE);
        binding.progressoPagamento.setProgress(progresso);
        binding.icAbrirListaLaches.setOnClickListener(view -> {
            doubleClick += 1;
            switch (doubleClick)
            {
                case 1:
                    binding.recyclerVerLanches.setVisibility(View.VISIBLE);
                    configRecycler();
                    break;
                case 2:
                    doubleClick = 0;
                    binding.recyclerVerLanches.setVisibility(View.GONE);
                    break;
            }
        });

        binding.viewStubPagamento.inflate();
        binding.viewStubPagamento.setVisibility(View.GONE);
        binding.btnProseguir.setOnClickListener(view -> validarCampos());

        binding.buscarCep.setOnClickListener(view -> {
            if (!binding.editCep.getText().toString().isEmpty()){
                buscarCepApi();
            }
        });


        retrofit = new Retrofit
                .Builder()
                .baseUrl("https://viacep.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void configRecycler()
    {
        RecyclerView.LayoutManager myLayoutManager = new LinearLayoutManager( getApplicationContext());
        binding.recyclerVerLanches.setLayoutManager( myLayoutManager );
        binding.recyclerVerLanches.setHasFixedSize( true );
        Adapter adapter = new Adapter(lanchesRecupe,getApplicationContext(),"PedidoFinalizadoActivity");
        binding.recyclerVerLanches.setAdapter(adapter);
    }
    public void recuperarDados(){
        lanchesRecupe = getIntent().getParcelableArrayListExtra("listaLanches");
        valorTotalRecuperado = getIntent().getDoubleExtra("ValorTotal",0);
        binding.ValorTotal.setText(FormatterValor.format(valorTotalRecuperado));
        valorFinal += valorTotalRecuperado;
        binding.valorFinalPagamento.setText(FormatterValor.format(valorFinal));
    }

    public void validarCampos(){
        if (!binding.editCep.getText().toString().isEmpty()){
            if (!binding.editRua.getText().toString().isEmpty()){
                if (!binding.editNumero.getText().toString().isEmpty()){
                    if (validarRadioButton()){
                        progresso = 2;
                        binding.progressoPagamento.setVisibility(View.VISIBLE);
                        binding.progressoPagamento.setProgress(progresso);

                        clickDouble+=1;
                        switch (clickDouble){
                            case 1:
                                binding.viewStubPagamento.setVisibility(View.VISIBLE);
                                binding.imgFormaPagamento.setVisibility(View.GONE);
                                binding.btnProseguir.setText("Voltar a tela inicial");
                                break;
                            case 2:
                                finish();
                                break;
                        }
                    }else {
                        Toast.makeText(this, "Escolha uma forma de pagamento!", Toast.LENGTH_SHORT).show();}
                }else {
                    Toast.makeText(this, "Adicione o numero da casa!", Toast.LENGTH_SHORT).show();}
            }else {
                Toast.makeText(this, "Adicione a rua!", Toast.LENGTH_SHORT).show();}
        }else {
            Toast.makeText(this, "Adicione o cep da rua!", Toast.LENGTH_SHORT).show();}
    }

    public boolean validarRadioButton(){
        if (binding.radioDinheiro.isChecked()){
            return true;
        }else if (binding.radioCartao.isChecked()){
            return true;
        }else{
            return false;
        }

    }

    public void buscarCepApi(){
        String numeroCep = binding.buscarCep.getText().toString();
        CepServices cepServices = retrofit.create(CepServices.class);
        Call<Cep> call = cepServices.recuperarCep(numeroCep);

       call.enqueue(new Callback<Cep>() {
           @Override
           public void onResponse(Call<Cep> call, Response<Cep> response) {
               if (response.isSuccessful()){
                   Cep cep = response.body();
                   binding.editRua.setText(cep.getLogradouro());
                   //editCep.setText(cep.getCep());
               }
           }

           @Override
           public void onFailure(Call<Cep> call, Throwable t){
               Toast.makeText(PedidoFinalizadoActivity.this, "Cep invalido", Toast.LENGTH_SHORT).show();

           }
       });



    }
}