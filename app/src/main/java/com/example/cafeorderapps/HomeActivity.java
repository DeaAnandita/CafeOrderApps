package com.example.cafeorderapps;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafeorderapps.Adapter.CategoriAdapter;
import com.example.cafeorderapps.Adapter.DataFilterAdapter;
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
    RecyclerView.LayoutManager mLayoutManager;
    CategoriAdapter categoriAdapter;
    ArrayList<HomeModel> homeModels;
    ArrayList<CategoriModel> categoriModels;
    ArrayList<HomeModel> searchModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        homeAdapter = new HomeAdapter(homeModels) {
        };
        recyclerView.setAdapter(homeAdapter);

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

        @Override
        public boolean onQueryTextChange(String nextText) {
            //Data akan berubah saat user menginputkan text/kata kunci pada SearchView
            nextText = nextText.toLowerCase();
            ArrayList<HomeModel> dataFilter = new ArrayList<>();
            for(HomeModel data : searchModels){
                String nama = data.getNama().toLowerCase();
                if(nama.contains(nextText)){
                    dataFilter.add(data);
                }
            }
            DataFilterAdapter.setFilter(dataFilter);
            return true;
        }
        });
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }



}
