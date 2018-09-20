package com.arbresystems.appoint.servicos;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface Usuario {
    //SharedPreferences sp =  getSharedPreferences(PREF_NAME, MODE_PRIVATE);

    @Headers({
            "x-authentication: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiIsImp0aSI6ImFwaUFwcG9pbnQifQ.eyJpc3MiOiJodHRwOlwvXC9hcmJyZXN5c3RlbXMuY29tIiwiYXVkIjoiaHR0cDpcL1wvYXBpLmFwcG9pbnQuYXJicmVzeXN0ZW1zLmNvbSIsImp0aSI6ImFwaUFwcG9pbnQiLCJpYXQiOjE1MzEwMjY5OTYsIm5iZiI6MTUzMTAyNzAwNiwiY2FkYXN0cm8iOiJvayJ9.tkwtjzxMcisXJ35VsqqZTf1Rqj-S3Q4uiF9xxzmTZuc",
            "x-audience: http://api.appoint.arbresystems.com",
            "x-id: apiAppoint"
    })
    @POST("/inserirUsuario")
    Call<com.arbresystems.appoint.model.Usuario> cadastro(@Body com.arbresystems.appoint.model.Usuario usuario);

    @Headers({
            "x-authentication: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiIsImp0aSI6ImFwaUFwcG9pbnQifQ.eyJpc3MiOiJodHRwOlwvXC9hcmJyZXN5c3RlbXMuY29tIiwiYXVkIjoiaHR0cDpcL1wvYXBpLmFwcG9pbnQuYXJicmVzeXN0ZW1zLmNvbSIsImp0aSI6ImFwaUFwcG9pbnQiLCJpYXQiOjE1MzEwMjcwNTksIm5iZiI6MTUzMTAyNzA2OSwibG9naW4iOiJvayJ9.LJwCf-0FQ395Fm4C0eihc_41c_cb3qEY41505UzZzb8",
            "x-audience: http://api.appoint.arbresystems.com",
            "x-id: apiAppoint"
    })
    @POST("/logar")
        //Call<com.arbresystems.appoint.model.Usuario> login(@Field("email") String email, @Field("telefone") String telefone, @Field("id") String id);
    Call<com.arbresystems.appoint.model.Usuario> login(@Body com.arbresystems.appoint.model.Usuario usuario);

    @Headers({
            "x-authentication: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiIsImp0aSI6ImFwaUFwcG9pbnQifQ.eyJpc3MiOiJodHRwOlwvXC9hcmJyZXN5c3RlbXMuY29tIiwiYXVkIjoiaHR0cDpcL1wvYXBpLmFwcG9pbnQuYXJicmVzeXN0ZW1zLmNvbSIsImp0aSI6ImFwaUFwcG9pbnQiLCJpYXQiOjE1MzEwMjcwNTksIm5iZiI6MTUzMTAyNzA2OSwibG9naW4iOiJvayJ9.LJwCf-0FQ395Fm4C0eihc_41c_cb3qEY41505UzZzb8",
            "x-audience: http://api.appoint.arbresystems.com",
            "x-id: apiAppoint"
    })
    @DELETE("/apagarUsuario")
    Call<com.arbresystems.appoint.model.Usuario> apagar(@Body com.arbresystems.appoint.model.Usuario usuario);
}
