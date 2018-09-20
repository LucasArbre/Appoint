package com.arbresystems.appoint.servicos;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Estabelecimento {

    @Headers({
            "x-audience: http://api.appoint.arbresystems.com",
            "x-id: apiAppoint"
    })
    @GET("/pesquisarEstabelecimento ")
    Call<com.arbresystems.appoint.model.Estabelecimento> pesquisar (
            @Header("x-authentication") String token,
            @Path("busca") String busca);
}
