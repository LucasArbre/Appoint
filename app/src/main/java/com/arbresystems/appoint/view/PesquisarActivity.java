package com.arbresystems.appoint.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.arbresystems.appoint.R;
import com.arbresystems.appoint.RetrofitConfig;
import com.arbresystems.appoint.adapters.AdapterItemPesquisaEstabelecimento;
import com.arbresystems.appoint.model.Estabelecimento;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.arbresystems.appoint.view.MainActivity.PREF_NAME;

public class PesquisarActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private AdapterItemPesquisaEstabelecimento adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private boolean isScrolling = false;
    private List<Estabelecimento> estabelecimentos;
    private int currentItem, totalIems, scrollOutItems;
    private ProgressBar progressBar = findViewById(R.id.progress);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);

        sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        //listView = findViewById(R.id.pesquisaAdm);
        manager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.rvPesquisa);
        recyclerView.setLayoutManager(manager);
        //adapter = new RecyclerViewDataAdapterServicos(this, servicos);

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

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItem = manager.getChildCount();
                totalIems = manager.getItemCount();
                scrollOutItems = manager.findFirstVisibleItemPosition();

                if (isScrolling && (currentItem+scrollOutItems == totalIems)){
                    isScrolling = false;
                    fetchData();
                }
            }

        });
    }

    private void fetchData() {
        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<5;i++){
                    //estabelecimentos.add();
                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                }
            }
        },5000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.menuPesquisa);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setSubmitButtonEnabled(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {

                new RetrofitConfig().getEstabelecimentoService().pesquisar(sp.getString("token", null), s).enqueue(new Callback<ArrayList<Estabelecimento>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Estabelecimento>> call, Response<ArrayList<Estabelecimento>> response) {
                        estabelecimentos = response.body();
                        Log.e("resposta", estabelecimentos.toString());
                        adapter = new AdapterItemPesquisaEstabelecimento(getApplicationContext(), estabelecimentos);
                        //listView.setAdapter(adapter);
                        recyclerView.setAdapter(adapter);
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