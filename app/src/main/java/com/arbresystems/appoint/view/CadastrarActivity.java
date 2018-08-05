package com.arbresystems.appoint.view;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arbresystems.appoint.R;
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

import static com.arbresystems.appoint.view.MainActivity.PREF_NAME;

public class CadastrarActivity extends AppCompatActivity {

    private static final  String TAG = "PhoneAuth";

    private EditText txtName;
    private EditText txtTel;
    private EditText txtCod;
    private Button btnCadastro;
    private ProgressBar mProgress;
    private Button btnVerificarCodigo;

    private String phoneVerificationId;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks verificationCallbacks;
    private PhoneAuthProvider.ForceResendingToken resendToken;

    private FirebaseAuth fbAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cadastrar);

        txtName = findViewById(R.id.txtName);
        txtTel = findViewById(R.id.txtTel);
        txtCod = findViewById(R.id.txtCod);
        btnCadastro = findViewById(R.id.btnCadastro);
        btnVerificarCodigo = findViewById(R.id.btnVerificarCodigo);

        mProgress = new ProgressBar(getApplicationContext());
        mProgress.setMax(100);

        final SharedPreferences sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        fbAuth = FirebaseAuth.getInstance();

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

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCode(v);
            }
        });

        btnVerificarCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyCode(v);
            }
        });

        mProgress.setProgress(100);
    }

    public void sendCode(View view){
        String phoneNumber = txtTel.getText().toString(); //para teste, dps mudar para um campo especifico de telefone

        setUpVerificatonCallbacks();

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                verificationCallbacks);        // OnVerificationStateChangedCallbacks
    }

    private void setUpVerificatonCallbacks(){
        verificationCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                //se td der certo
                signInWhithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                //se td der errado
                if (e instanceof FirebaseAuthInvalidCredentialsException){
                    Log.e(TAG, "Crendencial inválida: " + e.getLocalizedMessage());
                }else if(e instanceof FirebaseTooManyRequestsException){
                    Log.e(TAG, "Cota de SMS excedida!");
                }
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                phoneVerificationId = s;
                resendToken = forceResendingToken;
            }
        };
    }

    public void verifyCode(View view){
        //verifica o codigo
        String code = txtCod.getText().toString();

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(phoneVerificationId, code);
        signInWhithPhoneAuthCredential(credential);
    }

    private void signInWhithPhoneAuthCredential(PhoneAuthCredential credential){
        fbAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //acho que deu certo
                            FirebaseUser user = task.getResult().getUser();
                            Toast.makeText(getApplicationContext(), "Uau, codigo ok", Toast.LENGTH_SHORT).show();
                        }else{
                            if(task.getException() instanceof  FirebaseAuthInvalidCredentialsException){
                                //o código de verificação inserido era inválido
                                Toast.makeText(getApplicationContext(), "Código inválido", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    public void resendCode(View view){
        String phoneNumber = txtTel.getText().toString(); //para teste, dps mudar para um campo especifico de telefone

        setUpVerificatonCallbacks();

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                verificationCallbacks);        // OnVerificationStateChangedCallbacks
    }

    public void signOut(View view){
        //sair
        fbAuth.signOut();
    }
}
