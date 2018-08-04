package com.arbresystems.appoint.servicos;

import android.content.SharedPreferences;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static android.content.Context.MODE_PRIVATE;
import static com.arbresystems.appoint.view.MainActivity.PREF_NAME;

public interface Usuario {
    //SharedPreferences sp =  getSharedPreferences(PREF_NAME, MODE_PRIVATE);

    @Headers({
            "x-authentication: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiIsImp0aSI6ImFwaUFwcG9pbnQifQ.eyJpc3MiOiJodHRwOlwvXC9hcmJyZXN5c3RlbXMuY29tIiwiYXVkIjoiaHR0cDpcL1wvYXBpLmFwcG9pbnQuYXJicmVzeXN0ZW1zLmNvbSIsImp0aSI6ImFwaUFwcG9pbnQiLCJpYXQiOjE1MzEwMjY5OTYsIm5iZiI6MTUzMTAyNzAwNiwiY2FkYXN0cm8iOiJvayJ9.tkwtjzxMcisXJ35VsqqZTf1Rqj-S3Q4uiF9xxzmTZuc",
            "x-audience: http://api.appoint.arbresystems.com",
            "x-id: apiAppoint"
    })
    @POST("/inserirUsuario")
    Call<com.arbresystems.appoint.Usuario> cadastro(@Body com.arbresystems.appoint.Usuario usuario);

    @Headers({
            "x-authentication: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiIsImp0aSI6ImFwaUFwcG9pbnQifQ.eyJpc3MiOiJodHRwOlwvXC9hcmJyZXN5c3RlbXMuY29tIiwiYXVkIjoiaHR0cDpcL1wvYXBpLmFwcG9pbnQuYXJicmVzeXN0ZW1zLmNvbSIsImp0aSI6ImFwaUFwcG9pbnQiLCJpYXQiOjE1MzEwMjcwNTksIm5iZiI6MTUzMTAyNzA2OSwibG9naW4iOiJvayJ9.LJwCf-0FQ395Fm4C0eihc_41c_cb3qEY41505UzZzb8",
            "x-audience: http://api.appoint.arbresystems.com",
            "x-id: apiAppoint"
    })
    @POST("/logar")
    Call<com.arbresystems.appoint.Usuario> login(@Body com.arbresystems.appoint.Usuario usuario);

    @Headers({
            "x-authentication: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiIsImp0aSI6ImFwaUFwcG9pbnQifQ.eyJpc3MiOiJodHRwOlwvXC9hcmJyZXN5c3RlbXMuY29tIiwiYXVkIjoiaHR0cDpcL1wvYXBpLmFwcG9pbnQuYXJicmVzeXN0ZW1zLmNvbSIsImp0aSI6ImFwaUFwcG9pbnQiLCJpYXQiOjE1MzEwMjcwNTksIm5iZiI6MTUzMTAyNzA2OSwibG9naW4iOiJvayJ9.LJwCf-0FQ395Fm4C0eihc_41c_cb3qEY41505UzZzb8",
            "x-audience: http://api.appoint.arbresystems.com",
            "x-id: apiAppoint"
    })
    @DELETE("/apagarUsuario")
    Call<com.arbresystems.appoint.Usuario> apagar(@Body com.arbresystems.appoint.Usuario usuario);
}
