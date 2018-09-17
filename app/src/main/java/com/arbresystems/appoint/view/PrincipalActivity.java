package com.arbresystems.appoint.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.arbresystems.appoint.BottomNavigationViewHelper;
import com.arbresystems.appoint.R;

public class PrincipalActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
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
                        Intent intent0 = new Intent(PrincipalActivity.this, ActivityPesquisar.class);
                        startActivity(intent0);
                        break;

                    case R.id.nav_home:

                        break;

                    case R.id.nav_promos:
                        Intent intent2 = new Intent(PrincipalActivity.this, PromocoesActivity.class);
                        startActivity(intent2);
                        break;
                }


                return false;
            }
        });
        //mMainFrame = findViewById(R.id.main_frame);
        //mMainNav = findViewById(R.id.main_nav);
        //mMainNav.setOnNavigationItemSelectedListener(navListener);

       // getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new HomeFragment()).commit();

        /*homeFragment = new HomeFragment();
        searchFragment = new SearchFragment();
        menuFragment = new PromocoesFragment();


        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_home:
                        setFragment(homeFragment);
                        return true;
                    case R.id.nav_menu:
                        setFragment(menuFragment);
                        return true;
                    case R.id.nav_search:
                        setFragment(searchFragment);
                        return true;

                        default: return false;
                }

            }
        });*/
    }
/*    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_search:
                            selectedFragment = new SearchFragment();
                            break;
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_promos:
                            selectedFragment = new PromocoesFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,
                            selectedFragment).commit();
                    return true;
                }
            };



    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }*/
}
