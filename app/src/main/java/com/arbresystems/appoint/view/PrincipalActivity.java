package com.arbresystems.appoint.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.arbresystems.appoint.R;
import com.arbresystems.appoint.segundoPlano.atualizarLocalizacao.ServiceAtualizarLocalizacao;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseAuth;

import static com.arbresystems.appoint.view.MainActivity.PREF_NAME;

public class PrincipalActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private FirebaseAuth mAuth;
    private SharedPreferences sp;
    private SharedPreferences spm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        System.gc();
        System.gc();
        System.gc();

        startService(new Intent(this, ServiceAtualizarLocalizacao.class));

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        mAuth = FirebaseAuth.getInstance();
        sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        spm = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_search:
                        startActivity(new Intent(PrincipalActivity.this, PesquisarActivity.class));
                        finish();
                        break;

                    case R.id.nav_home:

                        break;

                    case R.id.nav_promos:
                        startActivity(new Intent(PrincipalActivity.this, PromocoesActivity.class));
                        finish();
                        break;
                }


                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.opt_configs){
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            StringBuilder info = new StringBuilder();

            info.append("Nome: " + sp.getString("key_nome_usr", ""));
            info.append("\nEmail: " + sp.getString("key_email_usr", ""));
            info.append("\nTelefone: " + sp.getString("key_telefone_usr", ""));
            //info.append("\nAtivar notificações: " + sp.getString("key_notificacoes_habilitar", "-1"));

            String nome = sp.getString("key_nome_usr", "");
            Log.e("testeNome", nome);
            Intent intent = new Intent(PrincipalActivity.this, ConfigActivity.class);
            startActivity(intent);
        }
        if(id == R.id.opt_sobre){
            Intent intent = new Intent(PrincipalActivity.this, SobreActivity.class);
            startActivity(intent);
        }
        if(id == R.id.opt_sair){
            LoginManager.getInstance().logOut();
            spm.edit().clear().commit();
            PreferenceManager.setDefaultValues(this, R.xml.preferences, true);
            mAuth.signOut();
            sp.edit().clear().commit();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
