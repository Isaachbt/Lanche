package com.curso.lanche.service;

import com.curso.lanche.model.Cep;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CepServices {

    @GET("{cep}/json/")
    Call<Cep> recuperarCep(@Path("cep") String cep);
}
