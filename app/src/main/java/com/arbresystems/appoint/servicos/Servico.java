package com.arbresystems.appoint.servicos;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface Servico {
    @Headers({
            "x-audience: http://api.appoint.arbresystems.com",
            "x-id: apiAppoint"
    })
    @GET("/pesquisarServico/{busca}")
    Call<ArrayList<com.arbresystems.appoint.model.Servico>> pesquisarServico (
            @Header("x-authentication") String token,
            @Path("busca") String busca);
}
