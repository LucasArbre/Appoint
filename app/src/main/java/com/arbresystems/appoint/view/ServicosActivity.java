package com.arbresystems.appoint.view;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.arbresystems.appoint.R;
import com.arbresystems.appoint.model.Servicos;
import com.arbresystems.appoint.viewModels.RecyclerViewDataAdapter;
import com.arbresystems.appoint.viewModels.RecyclerViewDataAdapterHorario;
import com.arbresystems.appoint.viewModels.RecyclerViewDataAdapterServicos;

import java.util.ArrayList;
import java.util.List;

public class ServicosActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private RecyclerView recyclerView;
    private ArrayList<Servicos> servicos;
    private RecyclerViewDataAdapterServicos adapter = new RecyclerViewDataAdapterServicos(this, servicos);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicos);

        recyclerView = findViewById(R.id.rvServicos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //adapter = new RecyclerViewDataAdapterServicos(this, servicos);
        recyclerView.setAdapter(adapter);
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
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        String userInput = s.toLowerCase();
        ArrayList<Servicos> newList = new ArrayList<>();

        for (Servicos servico : servicos) {
            if (servico.getNome().toLowerCase().contains(userInput)) {
                newList.add(servico);
            }
        }
        adapter.updateList(newList);
        return false;
    }
}
