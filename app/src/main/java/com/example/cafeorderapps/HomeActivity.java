package com.example.cafeorderapps;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.cafeorderapps.Fragment.CartFragment;
import com.example.cafeorderapps.Fragment.DrinkFragment;
import com.example.cafeorderapps.Fragment.FoodFragment;
import com.example.cafeorderapps.Model.HomeModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import io.realm.RealmResults;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView navigationView = findViewById(R.id.navigationView);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView.setOnNavigationItemSelectedListener(selectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DrinkFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener selectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()) {

                        case R.id.nav_drink:
                            selectedFragment = new DrinkFragment();
                            break;
                        case R.id.nav_cart:
                            selectedFragment = new CartFragment();
                            break;
                        case R.id.nav_food:
                            selectedFragment = new FoodFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };

}



