package com.arbresystems.appoint.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.arbresystems.appoint.R;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class MainActivity extends AppCompatActivity {
    public static String PREF_NAME = "SP";
    public static int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        //GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        //updateUI(account);

        SharedPreferences sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        if (sp.getString("token", null) == null){
            //login
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }else{
            //continua no app
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(getApplicationContext(), PrincipalActivity.class));
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }

    }
}
