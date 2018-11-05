package com.arbresystems.appoint.view;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.arbresystems.appoint.R;
import com.arbresystems.appoint.RetrofitConfig;
import com.arbresystems.appoint.adapters.AdapterItemServicos;
import com.arbresystems.appoint.model.Servico;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.arbresystems.appoint.view.MainActivity.PREF_NAME;

public class ServicosActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private RecyclerView recyclerView;
    private SharedPreferences sp;
    private ArrayList<Servico> servicos;
    private AdapterItemServicos adapterItemServicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicos);

        sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        recyclerView = findViewById(R.id.rvServicos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //adapter = new RecyclerViewDataAdapterServicos(this, servicos);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.menuPesquisa);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        new RetrofitConfig().getServicoService().pesquisarServico(sp.getString("token", null),s).enqueue(new Callback<ArrayList<Servico>>() {
            @Override
            public void onResponse(Call<ArrayList<Servico>> call, Response<ArrayList<Servico>> response) {
                servicos = response.body();
                Log.d("respostaServicos", servicos.toString());
                adapterItemServicos = new AdapterItemServicos(getApplicationContext(), servicos);
                recyclerView.setAdapter(adapterItemServicos);
            }

            @Override
            public void onFailure(Call<ArrayList<Servico>> call, Throwable t) {

            }
        });
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}
