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
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.arbresystems.appoint.view.MainActivity.PREF_NAME;

public class CadastrarActivity extends AppCompatActivity {

    private EditText txtName;
    private EditText txtEmail;
    private EditText txtTelefone;
    private EditText txtSenha;
    private EditText txtCpf;
    private EditText txtDtNascimento;
    private Button btnCadastro;
    private ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cadastrar);

        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtTelefone = findViewById(R.id.txtTelefone);
        txtSenha = findViewById(R.id.confirmarSenha);
        txtCpf = findViewById(R.id.txtCpf);
        txtDtNascimento = findViewById(R.id.txtDtNascimnnto);
        btnCadastro = findViewById(R.id.btnCadastro);

        mProgress = new ProgressBar(getApplicationContext());
        mProgress.setMax(100);

        final SharedPreferences sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgress.setProgress(50);

                Usuario usuario = new Usuario(txtName.getText().toString(), txtEmail.getText().toString(), txtTelefone.getText().toString(), txtSenha.getText().toString(), txtCpf.getText().toString(), txtDtNascimento.getText().toString());

                new RetrofitConfig().getCadastroService().cadastro(usuario).enqueue(
                        new Callback<Usuario>() {
                            @Override
                            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                                mProgress.setProgress(75);
                                if (response.isSuccessful()) {
                                    if (response.body().getErro()) {
                                        if (response.body().getDescricao().equals("usuario ja existe")) {
                                            mProgress.setProgress(100);
                                            Toast.makeText(getApplicationContext(), "Usuário ja existe!",
                                                    Toast.LENGTH_SHORT).show();
                                        } else {
                                            mProgress.setProgress(100);
                                            Toast.makeText(getApplicationContext(), "Erro cadastrar usuário!",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        SharedPreferences.Editor editor = sp.edit();
                                        editor.putString("token", response.body().getToken());
                                        editor.apply();
                                        Toast.makeText(getApplicationContext(), "Usuário cadastrado com sucesso!",
                                                Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<Usuario> call, Throwable t) {
                                mProgress.setProgress(75);
                                Log.e("erro", t.getMessage());
                                Toast.makeText(getApplicationContext(), "Impossível cadastrar usuário!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        });
        mProgress.setProgress(100);
    }
}
