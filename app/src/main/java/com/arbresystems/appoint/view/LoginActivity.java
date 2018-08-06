package com.arbresystems.appoint.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arbresystems.appoint.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import static com.arbresystems.appoint.view.MainActivity.PREF_NAME;

public class LoginActivity extends AppCompatActivity {
    private int a = 1;

    private static final String TAG = "PhoneAuth";
    private static final int RC_SIGN_IN = 1;

    private EditText editEmail;
    private EditText editSenha;
    private Button btnCadastrar;
    private Button btnEntrar;

    private CallbackManager callbackManager;
    private LoginButton loginButton;

    private GoogleApiClient mGoogleApiClient;
    private GoogleSignInClient mGoogleSignInClient;
    private SignInButton mSignInButton;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        mSignInButton = (SignInButton) findViewById(R.id.sign_in_button);

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(getApplicationContext(), "Erro ao conectar", Toast.LENGTH_SHORT).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a = 2;
                signIn();
            }
        });

        // Initialize Facebook Login button
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                // ...
            }
        });

        editSenha = findViewById(R.id.editSenha);
        editEmail = findViewById(R.id.editEmail);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnEntrar = findViewById(R.id.btnEntrar);

        final SharedPreferences sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (a == 1) {
            //face
            super.onActivityResult(requestCode, resultCode, data);
            callbackManager.onActivityResult(requestCode, resultCode, data);
        } else {
            //google
            super.onActivityResult(requestCode, resultCode, data);
            // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
            if (requestCode == RC_SIGN_IN) {
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    firebaseAuthWithGoogle(account);
                } catch (ApiException e) {
                    // Google Sign In failed, update UI appropriately
                    Log.w(TAG, "Google sign in failed", e);
                    // ...
                }
            }
        }
    }

    /*private void executeGraphRequest(String userId){
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
    }*/

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.e("email", user.getEmail());
                            Log.e("numero", user.getPhoneNumber());
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.e("email", user.getEmail());
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
