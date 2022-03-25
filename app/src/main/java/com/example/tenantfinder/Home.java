package com.example.tenantfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Home extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.men_home);
    }

    HomeFragment homeFragment = new HomeFragment();
    MyPropertyFragment myPropertyFragment = new MyPropertyFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.men_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, homeFragment).commit();
                return true;
            case R.id.men_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, profileFragment).commit();
                return true;
            case R.id.men_property:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, myPropertyFragment).commit();
                return true;
        }
        return false;
    }
}