package com.arbresystems.appoint.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.arbresystems.appoint.R;
import com.arbresystems.appoint.RetrofitConfig;
import com.arbresystems.appoint.adapters.AdapterItemHorario;
import com.arbresystems.appoint.model.Atendimento;
import com.arbresystems.appoint.model.Horario;
import com.arbresystems.appoint.segundoPlano.ServiceStart;
import com.arbresystems.appoint.segundoPlano.atualizarLocalizacao.Localizacao;
import com.arbresystems.appoint.segundoPlano.atualizarLocalizacao.ServiceAtualizarLocalizacao;
import com.arbresystems.appoint.viewModels.SectionDataModelHorario;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.arbresystems.appoint.view.MainActivity.PREF_NAME;

public class PrincipalActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private FirebaseAuth mAuth;
    private SharedPreferences sp;
    private SharedPreferences spm;

    private Localizacao localizacao = new Localizacao();
    private LocationManager locationManager = null;
    private Location l = null;

    private ArrayList<SectionDataModelHorario> allSampleData;

    private Button btnAgendar;

    private ArrayList<Atendimento> atendimentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        pedirPermissoes();

        mAuth = FirebaseAuth.getInstance();
        sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        spm = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        //recyclerView
        allSampleData = new ArrayList<SectionDataModelHorario>();

        createDummyData();

        RecyclerView my_recycler_view = (RecyclerView) findViewById(R.id.rvCompromissos);

        my_recycler_view.setHasFixedSize(true);

        AdapterItemHorario adapterItemHorario = new AdapterItemHorario(this, allSampleData);

        my_recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        my_recycler_view.setAdapter(adapterItemHorario);

        btnAgendar = findViewById(R.id.btnAgendar);
        btnAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(PrincipalActivity.this, ServicosActivity.class));
            }
        });

        //menu
        startService(new Intent(this, ServiceStart.class));
        //start de serviço que controla tudo em segundo plano


        System.gc();
        System.gc();
        System.gc();


        startService(new Intent(this, ServiceStart.class));
        //start de serviço que controla tudo em segundo plano

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_search:
                        overridePendingTransition(0, 0);
                        Intent i = new Intent(PrincipalActivity.this, PesquisarActivity.class);
                        i.addFlags(i.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(i);
                        finish();
                        break;

                    case R.id.nav_home:

                        break;

                    case R.id.nav_promos:
                        overridePendingTransition(0, 0);
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

    public void createDummyData() {
        //final ArrayList<String>[] datas = {null};

        new RetrofitConfig().getAtendimentoService().buscarAtendimentosAceitosENaoConcluidosPorUsuarioComDatasDistintas(sp.getString("token", null)).enqueue(new Callback<ArrayList<String>>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                //datas[0] = response.body();
                Log.d("respostaBuscarDatas", response.body().toString());
                Log.d("respostaBuscarAtendimentos", atendimentos.toString());
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                Log.e("erroBuscarAtendimentos", t.getMessage());
            }
        });

        for (int i = 1; i <= 5; i++) {

            Date a = new Date();

            SectionDataModelHorario dm = new SectionDataModelHorario();

            dm.setDia(a);
            dm.setDiaMes(a);

            dm.setAllItemsInSection(atendimentos);

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
        if (id == R.id.opt_configs) {
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
        if (id == R.id.opt_sobre) {
            Intent intent = new Intent(PrincipalActivity.this, SobreActivity.class);
            startActivity(intent);
        }
        if (id == R.id.opt_sair) {
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

    private void pedirPermissoes() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {

            //startService(new Intent(this, ServiceAtualizarLocalizacao.class));

            //LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            //Localizacao localizacao = new Localizacao(locationManager);


            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            String bestProvider = null;
            if (locationManager != null) {
                bestProvider = locationManager.getBestProvider(new Criteria(), true);
            }

            if (localizacao != null) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000,
                        0, localizacao);
            }

            if (bestProvider != null){
                l = locationManager.getLastKnownLocation(bestProvider);
            }

            if (l != null) {
                Log.d("longitude", String.valueOf(l.getLongitude()));
                Log.d("latitude", String.valueOf(String.valueOf(l.getLatitude())));
                Toast.makeText(this, "Latitude: " + l.getLatitude() + " " + "Longitude: " + l.getLongitude(), Toast.LENGTH_LONG).show();
                //Toast.makeText(this, "Longitude: " + l.getLongitude(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
