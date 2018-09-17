package com.arbresystems.appoint.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.arbresystems.appoint.BottomNavigationViewHelper;
import com.arbresystems.appoint.R;

public class ActivityPesquisar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
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
                        Intent intent0 = new Intent(ActivityPesquisar.this, PrincipalActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.nav_promos:
                        Intent intent2 = new Intent(ActivityPesquisar.this, PromocoesActivity.class);
                        startActivity(intent2);
                        break;
                }


                return false;
            }
        });
    }
}
