package com.arbresystems.appoint.servicos;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface Promocao {
    @Headers({
            "x-audience: http://api.appoint.arbresystems.com",
            "x-id: apiAppoint"
    })
    @GET("/buscarXPromocoes/{qtd}")
    Call<ArrayList<com.arbresystems.appoint.model.Promocao>> buscarXPromocoes (
            @Header("x-authentication") String token,
            @Path("qtd") String busca);

    @Headers({
            "x-audience: http://api.appoint.arbresystems.com",
            "x-id: apiAppoint"
    })
    @GET("/buscarPromocoes/")
    Call<ArrayList<com.arbresystems.appoint.model.Promocao>> buscarPromocoes (
            @Header("x-authentication") String token);
}
