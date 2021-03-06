package com.arbresystems.appoint.segundoPlano.atualizarConfiguracoes;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.arbresystems.appoint.RetrofitConfig;
import com.arbresystems.appoint.model.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.arbresystems.appoint.view.MainActivity.PREF_NAME;

public class ServiceAtualizarConfiguracoes  extends IntentService {

    public ServiceAtualizarConfiguracoes() {
        super("ServiceAtualizarConfiguracoes");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //codigo alterar configuracoes
        Usuario usuario = (Usuario) intent.getSerializableExtra("usuario");
        SharedPreferences sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        /*SharedPreferences spM = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Usuario usuario = new Usuario();
        usuario.setEmail(spM.getString("key_email_usr", ""));
        usuario.setNome(spM.getString("key_nome_usr", ""));
        usuario.setTelefone(spM.getString("key_telefone_usr", ""));*/

        Log.d("usuarioAlterar", usuario.toString());

        SharedPreferences.Editor editor = sp.edit();
        editor.putString("nomeUser", usuario.getNome());
        editor.putString("emailUser", usuario.getEmail());
        editor.putString("telefoneUser", usuario.getTelefone());
        editor.apply();

        new RetrofitConfig().getUsuarioService().atualizar(sp.getString("token", null), usuario).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.body().isErro()){
                    //erro
                    Log.e("erro", "erro ao atualizar usuario");
                }else{
                    //certo
                    Log.i("usuario", "usuario alterado com sucesso");
                }
                onDestroy();
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.e("erro", t.getMessage());
                Toast.makeText(getApplicationContext(), "Impossível atualizar configurações de usuário!",
                        Toast.LENGTH_SHORT).show();
                onDestroy();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
