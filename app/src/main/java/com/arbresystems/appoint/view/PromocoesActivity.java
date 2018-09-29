package com.arbresystems.appoint.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.arbresystems.appoint.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PromocoesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView listView = findViewById(R.id.lista_promos);

        setContentView(R.layout.activity_promocoes);
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
                        startActivity(new Intent(PromocoesActivity.this, PesquisarActivity.class));
                        finish();
                        break;

                    case R.id.nav_home:
                        startActivity(new Intent(PromocoesActivity.this, PrincipalActivity.class));
                        finish();
                        break;

                    case R.id.nav_promos:
                        break;
                }


                return false;
            }
        });

        ArrayList<String> soPraNaoDarErro = new ArrayList<>();

        AdapterItemPromocoes adapter = new AdapterItemPromocoes(this, R.layout.adapter_promos, soPraNaoDarErro);
        listView.setAdapter(adapter);
    }


}
