package com.curso.lanche;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {


    private TextView valorCompra,nmrBacon,nmrDogaoSimples,nmrDogaoEspecial,nmrCalabresa,nmrSalada,nmrTudo,nmrCalafrango;
    private double valorFinal = 0;
    private String convert,convetNmr;
    int x_tudo = 0,bacon = 0,dogaoSimples = 0, dogaoEspecial = 0, calabresa = 0, salada = 0, calafango = 0;
    private Button bntFinalizar;
    private String format = "00.00";
    private NumberFormat formatter = new DecimalFormat(format);
    String formatValoFinal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lanches);
        inicializarComponentes();
        convert = String.valueOf(valorFinal);

        if (valorFinal == 0){
            bntFinalizar.setEnabled(false);
        }


        bntFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            Intent i = new Intent(getApplicationContext(),PedidoFinalizadoActivity.class);
            i.putExtra("valorTotal",valorFinal);
            startActivity(i);
            }
        });
    }

    public void inicializarComponentes(){

        nmrBacon = findViewById(R.id.nmrXBacon);
        nmrCalabresa = findViewById(R.id.nmrCalabresa);
        nmrDogaoSimples = findViewById(R.id.nmrDogaoSimples);
        nmrCalafrango = findViewById(R.id.nmrCalafrango);
        nmrDogaoEspecial = findViewById(R.id.nmrDogaoEspecial);
        nmrSalada = findViewById(R.id.nmrSalada);
        nmrTudo = findViewById(R.id.nmrXTudo);
        valorCompra = findViewById(R.id.txt_Valor_compra);
        bntFinalizar = findViewById(R.id.btnFinalizarCompra);
    }

    public void onClick(View view){


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
            case R.id.dogao:
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
    }

    public void removeNmrLanche(View view){

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


    }

    public void totalPedidos(){//esse metodo mostra o total de lanche pedido

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


    }
}