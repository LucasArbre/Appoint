package com.arbresystems.appoint.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arbresystems.appoint.R;
import com.arbresystems.appoint.RetrofitConfig;
import com.arbresystems.appoint.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.arbresystems.appoint.view.MainActivity.PREF_NAME;

public class LoginActivity extends AppCompatActivity {
    private EditText editEmail;
    private EditText editSenha;
    private Button btnCadastro;
    private Button btnEntrar;
    private ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editSenha = findViewById(R.id.editSenha);
        editEmail = findViewById(R.id.editEmail);
        btnCadastro = findViewById(R.id.btnCadastroComFb);
        btnEntrar = findViewById(R.id.btnEntrar);

        final SharedPreferences sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        mProgress = new ProgressBar(getApplicationContext());
        mProgress.setMax(100);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CadastrarActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgress.setProgress(50);

                Usuario usuario = new Usuario();
                usuario.setSenha(editSenha.getText().toString());
                usuario.setEmail(editEmail.getText().toString());

                new RetrofitConfig().getLoginService().login(usuario).enqueue(
                        new Callback<Usuario>() {
                            @Override
                            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                                Log.e("response", response.body().toString());
                                mProgress.setProgress(75);
                                if (response.body().getErro()) {
                                    if (response.body().getDescricao().equals("usuario nao existe")) {
                                        mProgress.setProgress(100);
                                        Toast.makeText(getApplicationContext(), "Usuário não existe!",
                                                Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), CadastrarActivity.class);
                                        startActivity(intent);  
                                    } else if (response.body().getDescricao().equals("senha incorreta")) {
                                        mProgress.setProgress(100);
                                        Toast.makeText(getApplicationContext(), "Senha incorreta!",
                                                Toast.LENGTH_SHORT).show();
                                        editSenha.setText("");
                                    }
                                } else {
                                    SharedPreferences.Editor editor = sp.edit();
                                    editor.putString("token", response.body().getToken());
                                    editor.apply();
                                    Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }

                            @Override
                            public void onFailure(Call<Usuario> call, Throwable t) {
                                mProgress.setProgress(75);
                                Log.e("erro", t.getMessage());
                                Toast.makeText(getApplicationContext(), "Impossível logar!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        mProgress.setProgress(100);
    }
}
