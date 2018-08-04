package com.arbresystems.appoint;

import com.arbresystems.appoint.servicos.Usuario;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://api.appoint.arbresystems.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Usuario getCadastroService(){
        return this.retrofit.create(Usuario.class);
    }

    public Usuario getLoginService(){
        return this.retrofit.create(Usuario.class);
    }
}