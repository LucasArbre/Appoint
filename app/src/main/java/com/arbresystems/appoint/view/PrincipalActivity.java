package com.arbresystems.appoint.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.arbresystems.appoint.R;
import com.arbresystems.appoint.model.Horario;
import com.arbresystems.appoint.segundoPlano.ServiceStart;
import com.arbresystems.appoint.segundoPlano.atualizarLocalizacao.GetLocalizacao;
import com.arbresystems.appoint.segundoPlano.atualizarLocalizacao.ServiceAtualizarLocalizacao;
import com.arbresystems.appoint.viewModels.RecyclerViewDataAdapter;
import com.arbresystems.appoint.viewModels.RecyclerViewDataAdapterHorario;
import com.arbresystems.appoint.viewModels.SectionDataModel;
import com.arbresystems.appoint.viewModels.SectionDataModelHorario;
import com.arbresystems.appoint.viewModels.SingleItemModel;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Date;

import static com.arbresystems.appoint.view.MainActivity.PREF_NAME;

public class PrincipalActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private FirebaseAuth mAuth;
    private SharedPreferences sp;
    private SharedPreferences spm;

    ArrayList<SectionDataModelHorario> allSampleData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        //recyclerView
        allSampleData = new ArrayList<SectionDataModelHorario>();

        createDummyData();


        RecyclerView my_recycler_view = (RecyclerView) findViewById(R.id.rvCompromissos);

        my_recycler_view.setHasFixedSize(true);

        RecyclerViewDataAdapterHorario adapter = new RecyclerViewDataAdapterHorario(this, allSampleData);

        my_recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        my_recycler_view.setAdapter(adapter);

        //menu
        startService(new Intent(this, ServiceStart.class));
        //start de serviço que controla tudo em segundo plano


        System.gc();
        System.gc();
        System.gc();

        startService(new Intent(this, ServiceAtualizarLocalizacao.class));
        GetLocalizacao localizacao = new GetLocalizacao(getApplicationContext());
            Log.e("latitude2", String.valueOf(localizacao.getLatitude()));
            Log.e("longitude2", String.valueOf(localizacao.getLongitude()));

        startService(new Intent(this, ServiceStart.class));
        //start de serviço que controla tudo em segundo plano

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
                        overridePendingTransition(0,0);
                        Intent i = new Intent(PrincipalActivity.this, PesquisarActivity.class);
                        i.addFlags(i.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i);
                        finish();
                        break;

                    case R.id.nav_home:

                        break;

                    case R.id.nav_promos:
                        overridePendingTransition(0,0);
                        Intent intent1 = new Intent(PrincipalActivity.this, PromocoesActivity.class);
                        intent1.addFlags(intent1.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent1);
                        finish();
                        break;
                }


                return false;
            }
        });

    }

    public void createDummyData(){
        for (int i = 1; i <= 5; i++) {

            Date a = new Date();

            SectionDataModelHorario dm = new SectionDataModelHorario();

            dm.setDia(a);
            dm.setDiaMes(a);

            ArrayList<Horario> singleItem = new ArrayList<Horario>();
            for (int j = 1; j <= 5; j++) {
                singleItem.add(new Horario(a, a, a, "Unhas"));
            }

            dm.setAllItemsInSection(singleItem);

            allSampleData.add(dm);

        }
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
