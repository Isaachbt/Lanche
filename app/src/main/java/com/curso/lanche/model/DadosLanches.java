package com.curso.lanche.model;

public class DadosLanches {

    private String nomeLanche;
    private String descricao;
    private int ftLanche;
    private String valor;
    private int quanti_lanche = 0;

    public DadosLanches(String nomeLanche, String descricao, int ftLanche, String valor) {
        this.nomeLanche = nomeLanche;
        this.descricao = descricao;
        this.ftLanche = ftLanche;
        this.valor = valor;


    }

    public String getNomeLanche() {
        return nomeLanche;
    }

    public void setNomeLanche(String nomeLanche) {
        this.nomeLanche = nomeLanche;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getFtLanche() {
        return ftLanche;
    }

    public void setFtLanche(int ftLanche) {
        this.ftLanche = ftLanche;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getQuanti_lanche() {
        return quanti_lanche;
    }

    public void setQuanti_lanche(int quanti_lanche) {
        this.quanti_lanche = quanti_lanche;
    }

}
