package com.arbresystems.appoint.view;

import  android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.arbresystems.appoint.R;
import com.arbresystems.appoint.RetrofitConfig;
import com.arbresystems.appoint.adapters.AdapterItemPromocoes;
import com.arbresystems.appoint.model.Promocao;
import com.arbresystems.appoint.segundoPlano.ServiceStart;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.arbresystems.appoint.view.MainActivity.PREF_NAME;

public class PromocoesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView (R.layout.activity_promocoes);

        sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        recyclerView = (RecyclerView) findViewById(R.id.Rpview);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new RetrofitConfig().getPromocaoService().buscarPromocoes(sp.getString("token", null)).enqueue(new Callback<ArrayList<Promocao>>() {
            @Override
            public void onResponse(Call<ArrayList<Promocao>> call, Response<ArrayList<Promocao>> response) {
                Log.d("respostaPromocoes", String.valueOf(response.body()));
                List<Promocao> listItem = response.body();
                adapter = new AdapterItemPromocoes(listItem, getApplicationContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Promocao>> call, Throwable t) {
                Log.e("erroPromocao", t.getMessage());
            }
        });
        /*for(int i = 0; i<=10; i++) {
            Promocao promocao = new Promocao(
                    "55%",
                    "Promoção imperdivel!"+ i+1,
                    "Essa promoção dura apenas 18horas",
                    "local dahora");
            listItem.add(promocao);
        }*/

        startService(new Intent(this, ServiceStart.class));
        //start de serviço que controla tudo em segundo plano

       // ListView listView = findViewById(R.id.lista_promos);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_search:
                        overridePendingTransition(0,0);
                        Intent intent = new Intent(PromocoesActivity.this, PesquisarActivity.class);
                        intent.addFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        finish();
                        break;

                    case R.id.nav_home:
                        overridePendingTransition(0,0);
                        Intent intent1 = new Intent(PromocoesActivity.this, PrincipalActivity.class);
                        intent1.addFlags(intent1.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent1);
                        finish();
                        break;

                    case R.id.nav_promos:
                        break;
                }


                return false;
            }
        });

        //ArrayList<String> soPraNaoDarErro = new ArrayList<String>();
        //soPraNaoDarErro.add("uau");

        //AdapterItemPromocoes adapter = new AdapterItemPromocoes(this, R.layout.adapter_promos, soPraNaoDarErro);
        //listView.setAdapter(adapter);
    }


}
