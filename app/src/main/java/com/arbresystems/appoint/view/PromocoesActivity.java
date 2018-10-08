package com.arbresystems.appoint.view;

import  android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.arbresystems.appoint.R;
import com.arbresystems.appoint.segundoPlano.ServiceStart;

import java.util.ArrayList;

public class PromocoesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startService(new Intent(this, ServiceStart.class));
        //start de serviço que controla tudo em segundo plano

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
