package com.example.cafeorderapps;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafeorderapps.Adapter.CategoriAdapter;
import com.example.cafeorderapps.Adapter.HomeAdapter;
import com.example.cafeorderapps.Fragment.CartFragment;
import com.example.cafeorderapps.Fragment.DrinkFragment;
import com.example.cafeorderapps.Fragment.FoodFragment;
import com.example.cafeorderapps.Model.CategoriModel;
import com.example.cafeorderapps.Model.HomeModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView recyclerView1;
    HomeAdapter homeAdapter;
    CategoriAdapter categoriAdapter;
    ArrayList<HomeModel> homeModels;
    ArrayList<CategoriModel> categoriModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView navigationView = findViewById(R.id.navigationView);

        recyclerView = findViewById(R.id.list);
        recyclerView1 = findViewById(R.id.rvkategori);
        navigationView.setOnNavigationItemSelectedListener(selectedListener);

        homeModels = new ArrayList<>();
        homeAdapter = new HomeAdapter(homeModels);
        recyclerView.setAdapter(homeAdapter);

        categoriModels = new ArrayList<>();
        categoriModels.add(new CategoriModel("1", "Mochaccino"));
        categoriModels.add(new CategoriModel("2", "Susu"));
        categoriModels.add(new CategoriModel("3", "Espresso"));
        categoriModels.add(new CategoriModel("4", "Macchiato"));
        categoriModels.add(new CategoriModel("5", "Choco"));
        categoriModels.add(new CategoriModel("6", "Americano"));
        categoriAdapter = new CategoriAdapter(categoriModels);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(layoutManager);
        recyclerView1.setAdapter(categoriAdapter);

        homeModels = new ArrayList<>();
        homeModels.add(new HomeModel("1", "Mochaccino", "15.000,-"));
        homeModels.add(new HomeModel("2", "Susu Caramel", "15.000,-"));
        homeModels.add(new HomeModel("3", "Espresso", "15.000,-"));
        homeModels.add(new HomeModel("4", "Macchiato", "15.000,-"));
        homeModels.add(new HomeModel("5", "Choco Latte", "15.000,-"));
        homeModels.add(new HomeModel("6", "Americano", "15.000,-"));

        homeAdapter = new HomeAdapter(homeModels);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(homeAdapter);
    }

    BottomNavigationView.OnNavigationItemSelectedListener selectedListener =
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

