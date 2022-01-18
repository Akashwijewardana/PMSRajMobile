package com.example.pmsraj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView nav;
    private FragmentManager frag_man;
    private FragmentTransaction frag_tra;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String str = intent.getStringExtra("mail");
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();



        nav = findViewById(R.id.navigation);
//        loadFragment(new HomeFragment());

        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()){
                    case R.id.nav_home:
                       loadFragment(new HomeFragment());

                        break;

                    case R.id.nav_search:
                        loadFragment(new SearchFragment());

                        break;

                    case R.id.nav_cart:
                        loadFragment(new PrescriptionsFragment(str));
                        break;


                    case R.id.nav_user:


                        loadFragment(new ProfileFragment(str));
                        break;
                }
                return true;
            }
        });
    }



    private void loadFragment(Fragment fragment) {
        frag_man = getSupportFragmentManager();
        frag_tra=frag_man.beginTransaction();
        frag_tra.replace(R.id.frag_container,fragment);
        frag_tra.commit();

    }
}