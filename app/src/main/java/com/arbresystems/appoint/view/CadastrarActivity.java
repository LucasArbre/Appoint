package com.arbresystems.appoint.view;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arbresystems.appoint.R;
import com.arbresystems.appoint.RetrofitConfig;
import com.arbresystems.appoint.model.Usuario;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
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
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
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
                            new RetrofitConfig().getUsuarioService().cadastro(usuario).enqueue(
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

}
