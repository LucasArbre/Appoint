package com.arbresystems.appoint.servicos;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

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
    @GET("/buscarAtendimentosAceitosENaoConcluidosPorUsuario")
    Call<ArrayList<String>> buscarAtendimentosAceitosENaoConcluidosPorUsuarioComDatasDistintas (
            @Header("x-authentication") String token);
}
