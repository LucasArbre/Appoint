package com.arbresystems.appoint.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.arbresystems.appoint.BottomNavigationViewHelper;
import com.arbresystems.appoint.R;

import java.util.ArrayList;
import java.util.Arrays;

public class PesquisarActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);

        ListView lv = findViewById(R.id.pesquisaAdm);
        ArrayList<String> arrayEstabelecimentos = new ArrayList<>();
        arrayEstabelecimentos.addAll(Arrays.asList(getResources().getStringArray(R.array.my_estabelecimentos)));
        adapter = new ArrayAdapter<>(PesquisarActivity.this, android.R.layout.simple_list_item_1, arrayEstabelecimentos);
        lv.setAdapter(adapter);


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
                        Intent intent0 = new Intent(PesquisarActivity.this, PrincipalActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.nav_promos:
                        Intent intent2 = new Intent(PesquisarActivity.this, PromocoesActivity.class);
                        startActivity(intent2);
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
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
