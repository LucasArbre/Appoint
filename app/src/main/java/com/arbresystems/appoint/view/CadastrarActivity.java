package com.arbresystems.appoint.view;

import android.app.Dialog;
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

public class CadastrarActivity extends AppCompatActivity {
    private int a = 1;

    private static final String TAG = "PhoneAuth";
    private static final int RC_SIGN_IN = 1;

    private EditText txtName;
    private EditText txtTel;
    private Button btnCadastro;

    private String phoneVerificationId;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks verificationCallbacks;
    private FirebaseAuth fbAuth;

    private Dialog dialog;

    private String id;
    private String nome;
    private String telefone;

    private SharedPreferences sp;

    private Button btnFacebook;
    private CallbackManager callbackManager;
    private LoginButton loginButton;

    private GoogleApiClient mGoogleApiClient;
    private SignInButton mSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        //setContentView(R.layout.activity_cadastrar);

        dialog = new Dialog(this);

        txtName = findViewById(R.id.txtName);
        txtTel = findViewById(R.id.txtTel);
        //btnFacebook = findViewById(R.id.btnFB);
        //txtCod = findViewById(R.id.txtCod);
       // btnCadastro = findViewById(R.id.btnCadastro);
        //btnVerificarCodigo = findViewById(R.id.btnVerificarCodigo);

        sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        fbAuth = FirebaseAuth.getInstance();
        fbAuth.useAppLanguage();

        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.performClick();
            }
        });

        // Initialize Facebook Login button
        callbackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.login_button);
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

        mSignInButton = findViewById(R.id.sign_in_button);

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

        /*btnCadastro.setOnClickListener(new View.OnClickListener() {
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
        });*/

        /*btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCode(v);
            }
        });*/

        /*btnVerificarCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyCode(v);
            }
        });*/
    }

    public void sendCode(View view) {
        telefone = txtTel.getText().toString();
        nome = txtName.getText().toString(); //dps eu vejo oq eu faço com o nome KKKKK

        if (telefone.length() == 0 || nome.length() == 0) {
            Toast.makeText(getApplicationContext(), "Digite um nome e um número de telefone!",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Enviando código...",
                    Toast.LENGTH_LONG).show();

            setUpVerificatonCallbacks();

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

    private void setUpVerificatonCallbacks() {
        verificationCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                //se td der certo
                signInWhithPhoneAuthCredential(phoneAuthCredential);
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

    public void verifyCode(View view) {
        //verifica o codigo
        String code = "codigo";

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(phoneVerificationId, code);
        signInWhithPhoneAuthCredential(credential);
    }

    private void signInWhithPhoneAuthCredential(PhoneAuthCredential credential) {
        fbAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //acho que deu certo
                            FirebaseUser user = task.getResult().getUser();
                            id = user.getUid();
                            Toast.makeText(getApplicationContext(), "Código correto!", Toast.LENGTH_SHORT).show();

                            dialog.setContentView(R.layout.load);
                            dialog.show();

                            final Usuario usuario = new Usuario();
                            usuario.setNome(nome);
                            usuario.setId(id);
                            usuario.setTelefone(telefone);

                            Log.e("usuario", usuario.toString());
                            new RetrofitConfig().getCadastroService().cadastro(usuario).enqueue(
                                    new Callback<Usuario>() {

                                        @Override
                                        public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                                            if (response.isSuccessful()) {
                                                if (true) {
                                                    if (response.body().getDescricao().equals("usuario ja existe")) {
                                                        dialog.dismiss();

                                                        Toast.makeText(getApplicationContext(), "Usuário ja existe!",
                                                                Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        dialog.dismiss();
                                                        Toast.makeText(getApplicationContext(), "Erro cadastrar usuário!",
                                                                Toast.LENGTH_SHORT).show();
                                                    }
                                                } else {
                                                    //SharedPreferences.Editor editor = sp.edit();
                                                    //editor.putString("token", response.body().getToken());
                                                    //editor.apply();

                                                    dialog.dismiss();

                                                    Toast.makeText(getApplicationContext(), "Usuário cadastrado com sucesso!",
                                                            Toast.LENGTH_SHORT).show();
                                                    startActivity(new Intent(getApplicationContext(), PrincipalActivity.class));
                                                    onDestroy();
                                                }
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Usuario> call, Throwable t) {
                                            Log.e("erro", t.getMessage());
                                            Toast.makeText(getApplicationContext(), "Impossível cadastrar usuário!",
                                                    Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }
                                    });
                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                //o código de verificação inserido era inválido
                                Toast.makeText(getApplicationContext(), "Código inválido", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    public void signOut(View view) {
        //sair
        fbAuth.signOut();
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        fbAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = fbAuth.getCurrentUser();
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

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        fbAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = fbAuth.getCurrentUser();
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
