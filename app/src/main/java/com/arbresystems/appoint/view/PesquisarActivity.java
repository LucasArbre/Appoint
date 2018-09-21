package com.arbresystems.appoint.view;

import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.arbresystems.appoint.R;
import com.arbresystems.appoint.RetrofitConfig;
import com.arbresystems.appoint.model.Estabelecimento;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.arbresystems.appoint.view.MainActivity.PREF_NAME;

public class PesquisarActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private ListView listView;
    private AdapterItemPesquisaEstabelecimento adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);

        sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        listView = findViewById(R.id.pesquisaAdm);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_search:
                        break;

                    case R.id.nav_home:
                        startActivity(new Intent(PesquisarActivity.this, PrincipalActivity.class));
                        finish();
                        break;

                    case R.id.nav_promos:
                        startActivity(new Intent(PesquisarActivity.this, PromocoesActivity.class));
                        finish();
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.menuPesquisa);
        SearchView searchView = (SearchView) item.getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {

                new RetrofitConfig().getEstabelecimentoService().pesquisar(sp.getString("token", null), s).enqueue(new Callback<ArrayList<Estabelecimento>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Estabelecimento>> call, Response<ArrayList<Estabelecimento>> response) {
                        List<Estabelecimento> estabelecimentos = response.body();
                        Log.e("resposta", estabelecimentos.toString());
                        adapter = new AdapterItemPesquisaEstabelecimento(getApplicationContext(), estabelecimentos);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Estabelecimento>> call, Throwable t) {
                        Log.e("erro", t.getMessage());
                        //Log.e("respostaErrada", call.request())
                    }
                });

                /*
                new RetrofitConfig().getEstabelecimentoService().pesquisarr(sp.getString("token", null), s).enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        Log.e("resposta", response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        Log.e("erro", t.getMessage());
                    }
                });
                */

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
