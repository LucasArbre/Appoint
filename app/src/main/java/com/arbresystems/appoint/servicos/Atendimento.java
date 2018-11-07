package com.arbresystems.appoint.servicos;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface Atendimento {

    @Headers({
            "x-audience: http://api.appoint.arbresystems.com",
            "x-id: apiAppoint"
    })
    @GET("/buscarAtendimentosAceitosENaoConcluidosPorUsuario")
    Call<ArrayList<com.arbresystems.appoint.model.Atendimento>> buscarAceitosENaoConcluidosPorUsuario (
            @Header("x-authentication") String token);

    @Headers({
            "x-audience: http://api.appoint.arbresystems.com",
            "x-id: apiAppoint"
    })
    @GET("/buscarAtendimentosAceitosENaoConcluidosPorUsuarioComDatasDistintas")
    Call<ArrayList<String>> buscarAtendimentosAceitosENaoConcluidosPorUsuarioComDatasDistintas (
            @Header("x-authentication") String token);

    @Headers({
            "x-audience: http://api.appoint.arbresystems.com",
            "x-id: apiAppoint"
    })
    @GET("/buscarAtendimentosAceitosENaoConcluidosPorUsuarioPorData/{dia}")
    Call<ArrayList<com.arbresystems.appoint.model.Atendimento>> buscarAtendimentosAceitosENaoConcluidosPorUsuarioPorData (
            @Header("x-authentication") String token,
            @Path("dia") String dia);
}
