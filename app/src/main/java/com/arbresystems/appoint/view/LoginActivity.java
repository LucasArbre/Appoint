package com.arbresystems.appoint.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.arbresystems.appoint.R;
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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import static com.arbresystems.appoint.view.MainActivity.PREF_NAME;

public class LoginActivity extends AppCompatActivity {
    private EditText editEmail;
    private EditText editSenha;
    private Button btnCadastrar;
    private Button btnEntrar;
    private ProgressBar mProgress;
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                //startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                executeGraphRequest(loginResult.getAccessToken().getUserId());
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });

        editSenha = findViewById(R.id.editSenha);
        editEmail = findViewById(R.id.editEmail);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnEntrar = findViewById(R.id.btnEntrar);

        final SharedPreferences sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        mProgress = new ProgressBar(getApplicationContext());
        mProgress.setMax(100);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CadastrarActivity.class);
                startActivity(intent);
            }
        });

        /*btnEntrar.setOnClickListener(new View.OnClickListener() {
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
        });*/
        mProgress.setProgress(100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void executeGraphRequest(String userId){
        GraphRequest request = new GraphRequest(AccessToken.getCurrentAccessToken(), userId, null, HttpMethod.GET, new GraphRequest.Callback() {
            @Override
            public void onCompleted(GraphResponse response) {
                Log.i("FACEBOOK", response.getJSONObject().toString());
                String teste[] = Profile.getCurrentProfile().toString().split(",");
                String nome = Profile.getCurrentProfile().getName();
                String id = Profile.getCurrentProfile().getId();
                Log.i("nome", nome);
                Log.i("id", id);
                Log.i("teste", teste.toString());
                for (String a : teste){
                    Log.i("a", a);
                }
            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, name, email, gender, birthday");
        request.setParameters(parameters);
        request.executeAsync();
    }
}
