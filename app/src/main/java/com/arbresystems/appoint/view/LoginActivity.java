package com.arbresystems.appoint.view;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arbresystems.appoint.R;
import com.arbresystems.appoint.RetrofitConfig;
import com.arbresystems.appoint.Usuario;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.arbresystems.appoint.view.MainActivity.PREF_NAME;

public class LoginActivity extends AppCompatActivity {
    private int a = 1;

    private static final String TAG = "PhoneAuth";
    private static final int RC_SIGN_IN = 1;

    private Button btnEntrar;
    private Button btnFacebook;
    private Button btnGoogle;
    private Button btnTestarApp;

    private CallbackManager callbackManager;
    private LoginButton loginButton;

    private GoogleApiClient mGoogleApiClient;
    private SignInButton mSignInButton;

    private FirebaseAuth mAuth;

    private Dialog janela;

    private SharedPreferences sp;

    private String nome;
    private String telefone;
    private EditText txtName;
    private EditText txtTel;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks verificationCallbacks;
    private String phoneVerificationId;
    private Usuario usuario;

    private Dialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        janela = new Dialog(this);
        load = new Dialog(this);

        btnFacebook = findViewById(R.id.btnFacebook);
        btnGoogle = findViewById(R.id.btnGoogle);
        txtName = findViewById(R.id.txtName);
        txtTel = findViewById(R.id.txtTel);

        btnTestarApp = findViewById(R.id.btnTestarApp);
        btnTestarApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, PrincipalActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mAuth = FirebaseAuth.getInstance();

        mSignInButton = (SignInButton) findViewById(R.id.sign_in_button);

        sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

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

        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a = 2;
                signIn();
                Log.e("teste2", "teste2");
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
                Toast.makeText(getApplicationContext(), "Erro ao entrar com Facebook!",
                        Toast.LENGTH_SHORT).show();
            }
        });


        btnEntrar = findViewById(R.id.btnEntrar);

        final SharedPreferences sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarPopUp(v);
            }
        });

        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("teste", "teste");
                loginButton.performClick();
            }
        });
    }

    public void mostrarPopUp(View v){
        janela.setContentView(R.layout.custom_pop_up);
        final EditText codigo = (EditText) janela.findViewById(R.id.editCodigo);
        Button confirma =(Button) janela.findViewById(R.id.btnConfirma);
        Button cancela =(Button) janela.findViewById(R.id.btnCancela);

        confirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //confirma o codigo
                if (codigo.getText().length() > 0) {
                    verifyCode(codigo.getText().toString(), v);
                } else {
                    Toast.makeText(getApplicationContext(), "Digite um codigo...",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fecha o pop-up
                janela.dismiss();
            }
        });
        janela.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        janela.show();
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

    private void handleFacebookAccessToken(AccessToken token) {
        load();
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
                            usuario = new Usuario();
                            usuario.setEmail(user.getEmail());
                            usuario.setNome(user.getDisplayName());
                            usuario.setId(user.getUid());
                            cadastrarUsuario(usuario);
                            //startActivity(new Intent(getApplicationContext(), PrincipalActivity.class));
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

    public void sendCode(View view) {
        telefone = txtTel.getText().toString();
        nome = txtName.getText().toString(); //

        if (telefone.length() == 0 || nome.length() == 0) {
            Toast.makeText(getApplicationContext(), "Digite um nome e um número de telefone!",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Enviando código...",
                    Toast.LENGTH_LONG).show();

            setUpVerificatonCallbacks(view);

            //btnVerificarCodigo.setEnabled(true);
            //txtCod.setEnabled(true);

            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    telefone,        // Phone number to verify
                    60,                 // Timeout duration
                    TimeUnit.SECONDS,   // Unit of timeout
                    this,               // Activity (for callback binding)
                    verificationCallbacks);        // OnVerificationStateChangedCallbacks
        }
    }

    private void setUpVerificatonCallbacks(final View v) {
        verificationCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                //se td der certo
                signInWhithPhoneAuthCredential(phoneAuthCredential, v);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                //se td der errado
                Toast.makeText(getApplicationContext(), "Erro ao processar o envio!",
                        Toast.LENGTH_SHORT).show();
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    Log.e(TAG, "Crendencial inválida: " + e.getLocalizedMessage());
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    Log.e(TAG, "Cota de SMS excedida!");
                }
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                phoneVerificationId = s;
            }
        };
    }

    private void signInWhithPhoneAuthCredential(PhoneAuthCredential credential, final View v) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //acho que deu certo
                            FirebaseUser user = task.getResult().getUser();
                            Toast.makeText(getApplicationContext(), "Código correto!", Toast.LENGTH_SHORT).show();

                            usuario = new Usuario();
                            usuario.setNome(nome);
                            usuario.setId(user.getUid());
                            usuario.setTelefone(telefone);
                            usuario.setTelefone(user.getPhoneNumber());

                            cadastrarUsuario(usuario);
                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                //o código de verificação inserido era inválido
                                Toast.makeText(getApplicationContext(), "Código inválido", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    public void verifyCode(String code, View view) {
        //verifica o codigo
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(phoneVerificationId, code);
        signInWhithPhoneAuthCredential(credential, view);
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        load();
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
                            if (user != null) {
                                Log.e("user", user.toString());
                                usuario = new Usuario();
                                usuario.setEmail(user.getEmail());
                                usuario.setNome(user.getDisplayName());
                                usuario.setId(user.getUid());
                                usuario.setTelefone(user.getPhoneNumber());
                                cadastrarUsuario(usuario);
                            }
                            //startActivity(new Intent(getApplicationContext(), PrincipalActivity.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void cadastrarUsuario(final Usuario usuario) {
        Log.e("usuarioCadastrar", usuario.toString());

        new RetrofitConfig().getUsuarioService().cadastro(usuario).enqueue(
                new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        Log.e("resposta", response.body().toString());
                        if (response.isSuccessful()) {
                            if (response.body().isErro()) {
                                if (response.body().getDescricao().equals("usuario ja existe")) {
                                    logarUsuario(usuario);
                                } else {
                                    load.dismiss();
                                    Toast.makeText(getApplicationContext(), "Erro cadastrar usuário!",
                                            Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                load.dismiss();
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("token", response.body().getToken());
                                editor.apply();
                                Toast.makeText(getApplicationContext(), "Usuário cadastrado com sucesso!",
                                        Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), PrincipalActivity.class));
                                finish();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        load.dismiss();
                        Log.e("erro", t.getMessage());
                        Toast.makeText(getApplicationContext(), "Impossível cadastrar usuário!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    public void logarUsuario(Usuario usuario) {
        Log.e("usuarioLogar", usuario.toString());


        //new RetrofitConfig().getUsuarioService().login(usuario.getEmail(), usuario.getTelefone(), usuario.getId()).enqueue(
        new RetrofitConfig().getUsuarioService().login(usuario).enqueue(
                new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        load.dismiss();
                        Log.e("resposta", response.body().toString());
                        if (response.isSuccessful()) {
                            if (response.body().isErro()) {
                                if (response.body().getDescricao().equals("usuario nao existe")) {
                                    Toast.makeText(getApplicationContext(), "Usuário não existe!",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Erro logar usuário!",
                                            Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                if (response.body().getDescricao().equals("usuario logado")) {
                                    SharedPreferences.Editor editor = sp.edit();
                                    editor.putString("token", response.body().getToken());
                                    editor.apply();
                                    startActivity(new Intent(getApplicationContext(), PrincipalActivity.class));
                                    finish();
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        load.dismiss();
                        Log.e("erro", t.getMessage());
                        Toast.makeText(getApplicationContext(), "Impossível logar usuário!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    public void load() {
        load.setContentView(R.layout.load);
        load.show();
        load.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}
