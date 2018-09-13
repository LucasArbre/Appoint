package com.arbresystems.appoint.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.arbresystems.appoint.R;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchFragment extends Fragment {


    public SearchFragment() {
        // Required empty public constructor
    }

    private ListView pesquisaAdm;
    private ArrayAdapter<String> adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pesquisaAdm = pesquisaAdm.findViewById(R.id.pesquisaAdm);
        ArrayList<String> estabelecimentos = new ArrayList<>();
        estabelecimentos.addAll(Arrays.asList(getResources().getStringArray(R.array.my_estabelecimentos)));
        adapter = new ArrayAdapter<String>(
                getActivity().getApplicationContext(),
                android.R.layout.simple_list_item_1,
                estabelecimentos
        );
        pesquisaAdm.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        //MenuInflater inflater1 = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.pesquisaAdm);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        //return super.onCreateOptionsMenu(menu);
    }

}
