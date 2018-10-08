package com.arbresystems.appoint.view;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.arbresystems.appoint.R;
import com.arbresystems.appoint.model.Estabelecimentos;

import java.util.ArrayList;

public class SearchableActivity extends AppCompatActivity {
    private ArrayList<Estabelecimentos> array;
    private ArrayList<String> listAux;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleSearch(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleSearch(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView;
        MenuItem item = menu.findItem(R.id.menuPesquisa);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB){
            searchView = (SearchView) item.getActionView();
        }else{
            searchView = (SearchView) MenuItemCompat.getActionView(item);
        }
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint(getResources().getString(R.string.search_hint));
        return true;

    }

    public void handleSearch(Intent i){
        if (Intent.ACTION_SEARCH.equalsIgnoreCase(i.getAction())){
            String q = i.getStringExtra(SearchManager.QUERY);
            getActionBar().setTitle(q);
            filter(q);
        }
    }

    public void filter(String q){
        listAux.clear();
        for (int i = 0, tamI = array.size(); i < tamI; i++){
            if(array.get(i).getNome().toLowerCase().startsWith(q)){
                listAux.add(array.get(i).getNome());
            }
        }
        for (int i = 0, tamI = array.size(); i < tamI; i++){
            if(!listAux.contains(array.get(i).getNome())
                    && array.get(i).getTipo().toLowerCase().startsWith(q)){

                listAux.add(array.get(i).getNome());
            }
        }
    }
}
