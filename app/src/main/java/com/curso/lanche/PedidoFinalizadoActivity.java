package com.curso.lanche;

import androidx.appcompat.app.AppCompatActivity;

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

import java.text.DecimalFormat;
import java.text.NumberFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PedidoFinalizadoActivity extends AppCompatActivity {
    private RadioButton  btnDinheior,btnCartao;
    private ProgressBar progressBar;
    private EditText editCep,editRua,editNumero;
    private Button btnProseguir,btnBuscar;
    private ViewStub viewStub;
    private int clickDouble = 0,progresso = 1;
    private TextView txtValorTotal,txtValorFinal;
    private double valorFinal = 4.00,valorTotal;
    private Retrofit retrofit;
    private String format = "00.00";
    private NumberFormat formatter = new DecimalFormat(format);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_finalizado);
        findView();
        recuperarDados();
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setProgress(progresso);



        viewStub.inflate();
        viewStub.setVisibility(View.GONE);
        btnProseguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            validarCampos();
            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editCep.getText().toString().isEmpty()){
                    buscarCepApi();
                }
            }
        });


        retrofit = new Retrofit
                .Builder()
                .baseUrl("https://viacep.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void recuperarDados(){
        Intent intent = getIntent();
        valorTotal = intent.getDoubleExtra("valorTotal",0);
        String formatValorTotal = formatter.format(valorTotal);
        txtValorTotal.setText(formatValorTotal);
        valorFinal += valorTotal;
        String formatValoFinal = formatter.format(valorFinal);
        txtValorFinal.setText(formatValoFinal);
    }

    public void findView(){
        btnDinheior = findViewById(R.id.radioDinheiro);
        btnCartao = findViewById(R.id.radioCartao);
        btnProseguir = findViewById(R.id.btnProseguir);
        progressBar = findViewById(R.id.progressoPagamento);
        editCep = findViewById(R.id.editCep);
        editNumero = findViewById(R.id.editNumero);
        editRua = findViewById(R.id.editRua);
        txtValorTotal = findViewById(R.id.ValorTotal);
        txtValorFinal = findViewById(R.id.valorFinalPagamento);
        viewStub = findViewById(R.id.viewStubPagamento);
        btnBuscar = findViewById(R.id.buscarCep);


    }

    public void validarCampos(){
        if (!editCep.getText().toString().isEmpty()){
            if (!editRua.getText().toString().isEmpty()){
                if (!editNumero.getText().toString().isEmpty()){
                    if (validarRadioButton()){
                        progresso = 2;
                        progressBar.setVisibility(View.VISIBLE);
                        progressBar.setProgress(progresso);

                        clickDouble+=1;
                        switch (clickDouble){
                            case 1:
                                viewStub.setVisibility(View.VISIBLE);
                                ImageView img = (ImageView) findViewById(R.id.imgFormaPagamento);
                                img.setVisibility(View.GONE);
                                btnProseguir.setText("Voltar a tela inicial");
                                break;
                            case 2:
                                finish();
                                break;
                        }
                    }
                }
            }
        }
    }

    public boolean validarRadioButton(){
        if (btnDinheior.isChecked()){
            return true;
        }else if (btnCartao.isChecked()){
            return true;
        }else{
            return false;
        }

    }

    public void buscarCepApi(){
        String numeroCep = editCep.getText().toString();
        CepServices cepServices = retrofit.create(CepServices.class);
        Call<Cep> call = cepServices.recuperarCep(numeroCep);

       call.enqueue(new Callback<Cep>() {
           @Override
           public void onResponse(Call<Cep> call, Response<Cep> response) {
               if (response.isSuccessful()){
                   Cep cep = response.body();
                   editRua.setText(cep.getLogradouro());
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