package com.example.cafeorderapps;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    HomeAdapter homeAdapter;
    ArrayList<HomeModel> homeModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.list);

        homeModels = new ArrayList<>();
        homeModels.add(new HomeModel("1", "Mochachino", "15.000,-"));
        homeModels.add(new HomeModel("2","Susu Caramel", "15.000,-"));
        homeModels.add(new HomeModel("3","Espresso", "15.000,-"));
        homeModels.add(new HomeModel("4","Macchiato", "15.000,-"));
        homeModels.add(new HomeModel("5","Choco Latte", "15.000,-"));
        homeModels.add(new HomeModel("6","Americano", "15.000,-"));

        homeAdapter = new HomeAdapter(homeModels);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(homeAdapter);

    }
}
