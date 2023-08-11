package com.curso.lanche.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DadosLanches implements Parcelable {
    private String nomeLanche;
    private String descricao;
    private String ftLanche;
    private double valor;
    private int quanti_lanche = 0;

    public DadosLanches(String nomeLanche, String descricao, String ftLanche, double valor) {
        this.nomeLanche = nomeLanche;
        this.descricao = descricao;
        this.ftLanche = ftLanche;
        this.valor = valor;
    }

    protected DadosLanches(Parcel in) {
        nomeLanche = in.readString();
        descricao = in.readString();
        ftLanche = in.readString();
        valor = in.readDouble();
        quanti_lanche = in.readInt();
    }

    public static final Creator<DadosLanches> CREATOR = new Creator<DadosLanches>() {
        @Override
        public DadosLanches createFromParcel(Parcel in) {
            return new DadosLanches(in);
        }

        @Override
        public DadosLanches[] newArray(int size) {
            return new DadosLanches[size];
        }
    };

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

    public String getFtLanche() {
        return ftLanche;
    }

    public void setFtLanche(String ftLanche) {
        this.ftLanche = ftLanche;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuanti_lanche() {
        return quanti_lanche;
    }

    public void setQuanti_lanche(int quanti_lanche) {
        this.quanti_lanche = quanti_lanche;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nomeLanche);
        dest.writeString(descricao);
        dest.writeString(ftLanche);
        dest.writeDouble(valor);
        dest.writeInt(quanti_lanche);
    }
}
