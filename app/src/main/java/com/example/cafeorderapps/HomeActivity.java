package com.example.cafeorderapps;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    RecyclerView recyclerView1;
    public HomeAdapter homeAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    CategoriAdapter categoriAdapter;
    public List<HomeModel> homeModels;
    List<CategoriModel> categoriModels;
    List<HomeModel> searchModels;

    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        searchModels = new ArrayList<>();
        getData();
        adapter = new ArrayAdapter<HomeAdapter>(this, R.layout.support_simple_spinner_dropdown_item);

        Toolbar toolbar = findViewById(R.id.toolbar);
        BottomNavigationView navigationView = findViewById(R.id.navigationView);
        recyclerView = findViewById(R.id.list);
        recyclerView1 = findViewById(R.id.rvkategori);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DrinkFragment()).commit();

        setSupportActionBar(toolbar);

        navigationView.setOnNavigationItemSelectedListener(selectedListener);

        categoriModels = new ArrayList<>();
        categoriModels.add(new CategoriModel("1", "Mochaccino", false));
        categoriModels.add(new CategoriModel("2", "Susu", false));
        categoriModels.add(new CategoriModel("3", "Espresso", false));
        categoriModels.add(new CategoriModel("4", "Macchiato", false));
        categoriModels.add(new CategoriModel("5", "Choco", false));
        categoriModels.add(new CategoriModel("6", "Americano", false));
        categoriAdapter = new CategoriAdapter(categoriModels);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(layoutManager);
        recyclerView1.setAdapter(categoriAdapter);

        homeModels = new ArrayList<>();
        homeModels.add(new HomeModel("1", "Mochaccino", "15.000,-", false));
        homeModels.add(new HomeModel("2", "Susu Caramel", "15.000,-", false));
        homeModels.add(new HomeModel("3", "Espresso", "15.000,-", false));
        homeModels.add(new HomeModel("4", "Macchiato", "15.000,-", false));
        homeModels.add(new HomeModel("5", "Choco Latte", "15.000,-", false));
        homeModels.add(new HomeModel("6", "Americano", "15.000,-", false));

        homeAdapter = new HomeAdapter(this, homeModels);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(homeAdapter);
    }

    private void getData() {
        searchModels = new ArrayList<>();
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

