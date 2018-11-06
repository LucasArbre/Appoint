package com.arbresystems.appoint.servicos;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Estabelecimento {
    //eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiIsImp0aSI6ImFwaUFwcG9pbnQifQ.eyJpc3MiOiJodHRwOlwvXC9hcmJyZXN5c3RlbXMuY29tIiwiYXVkIjoiaHR0cDpcL1wvYXBpLmFwcG9pbnQuYXJicmVzeXN0ZW1zLmNvbSIsImp0aSI6ImFwaUFwcG9pbnQiLCJpYXQiOjE1MzEwODcyNDksIm5iZiI6MTUzMTA4NzI1OSwiY2FkYXN0cm9Fc3RhYmVsZWNpbWVudG8iOiJvayJ9.9AJOO7q1f1T7SKX6V9iV10r0rbaZz-SB8Nn8J-u4jBs
    @Headers({
            "x-authentication: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiIsImp0aSI6ImFwaUFwcG9pbnQifQ.eyJpc3MiOiJodHRwOlwvXC9hcmJyZXN5c3RlbXMuY29tIiwiYXVkIjoiaHR0cDpcL1wvYXBpLmFwcG9pbnQuYXJicmVzeXN0ZW1zLmNvbSIsImp0aSI6ImFwaUFwcG9pbnQiLCJpYXQiOjE1MzEwODcyNDksIm5iZiI6MTUzMTA4NzI1OSwiY2FkYXN0cm9Fc3RhYmVsZWNpbWVudG8iOiJvayJ9.9AJOO7q1f1T7SKX6V9iV10r0rbaZz-SB8Nn8J-u4jBs",
            "x-audience: http://api.appoint.arbresystems.com",
            "x-id: apiAppoint"
    })
    @POST("/inserirEstabelecimento")
    Call<com.arbresystems.appoint.model.Estabelecimento> cadastro(@Body com.arbresystems.appoint.model.Estabelecimento estabelecimento);

    @Headers({
            "x-audience: http://api.appoint.arbresystems.com",
            "x-id: apiAppoint"
    })
    @GET("/pesquisarEstabelecimento/{busca}")
    Call<ArrayList<com.arbresystems.appoint.model.Estabelecimento>> pesquisar (
            @Header("x-authentication") String token,
            @Path("busca") String busca);

    @Headers({
            "x-audience: http://api.appoint.arbresystems.com",
            "x-id: apiAppoint"
    })
    @GET("/pesquisarEstabelecimento/{busca}")
    Call<Object> pesquisarr (
            @Header("x-authentication") String token,
            @Path("busca") String busca);

    //pode estar errado o link ou o path, pode ser query
}
