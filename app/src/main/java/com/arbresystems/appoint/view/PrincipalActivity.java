package com.arbresystems.appoint.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.arbresystems.appoint.BottomNavigationViewHelper;
import com.arbresystems.appoint.R;
import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseAuth;

import static com.arbresystems.appoint.view.MainActivity.PREF_NAME;

public class PrincipalActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private FirebaseAuth mAuth;
    private SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        System.gc();
        System.gc();
        System.gc();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        mAuth = FirebaseAuth.getInstance();
        sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_search:
                        break;

                    case R.id.nav_home:
                        startActivity(new Intent(PrincipalActivity.this, PrincipalActivity.class));
                        finish();
                        break;

                    case R.id.nav_promos:
                        startActivity(new Intent(PrincipalActivity.this, PromocoesActivity.class));
                        finish();
                        break;
                }
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.opt_configs){
            Intent intent = new Intent(PrincipalActivity.this, ConfigActivity.class);
            startActivity(intent);
        }
        if(id==R.id.opt_sobre){
            Intent intent = new Intent(PrincipalActivity.this, SobreActivity.class);
            startActivity(intent);
        }
        if(id==R.id.opt_sair){
            mAuth.signOut();
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("token", null);
            editor.apply();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
