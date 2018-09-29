package com.arbresystems.appoint.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.arbresystems.appoint.R;
import com.arbresystems.appoint.segundoPlano.atualizarLocalizacao.ServiceAtualizarLocalizacao;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class MainActivity extends AppCompatActivity {
    public static String PREF_NAME = "SP";
    public static int SPLASH_TIME_OUT = 3000;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar().hide();

        progressBar = findViewById(R.id.pb);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        //GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        //updateUI(account);

        final SharedPreferences sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        //continua no app
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++){
                    progressBar.setProgress(i);
                }
                if (sp.getString("token", null) == null) {
                    //login
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    System.gc();
                    finish();
                } else {
                    startActivity(new Intent(getApplicationContext(), PrincipalActivity.class));
                    System.gc();
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);
    }
}
