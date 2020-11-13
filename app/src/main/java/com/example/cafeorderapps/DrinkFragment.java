package com.example.cafeorderapps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DrinkFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView recyclerView1;
    HomeAdapter homeAdapter;
    CategoriAdapter categoriAdapter;
    ArrayList<HomeModel> homeModels;
    ArrayList<CategoriModel> categoriModels;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.drink_fragment, container, true);
//
//        recyclerView = recyclerView.findViewById(R.id.list);
//        recyclerView1 = recyclerView1.findViewById(R.id.rvkategori);
//
//        categoriModels = new ArrayList<>();
//        categoriModels.add(new CategoriModel("1", "Mochaccino"));
//        categoriModels.add(new CategoriModel("2", "Susu"));
//        categoriModels.add(new CategoriModel("3", "Espresso"));
//        categoriModels.add(new CategoriModel("4", "Macchiato"));
//        categoriModels.add(new CategoriModel("5", "Choco"));
//        categoriModels.add(new CategoriModel("6", "Americano"));
//        categoriAdapter = new CategoriAdapter(categoriModels);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        recyclerView1.setLayoutManager(layoutManager);
//        recyclerView1.setAdapter(categoriAdapter);
//
//        homeModels = new ArrayList<>();
//        homeModels.add(new HomeModel("1", "Mochaccino", "15.000,-"));
//        homeModels.add(new HomeModel("2", "Susu Caramel", "15.000,-"));
//        homeModels.add(new HomeModel("3", "Espresso", "15.000,-"));
//        homeModels.add(new HomeModel("4", "Macchiato", "15.000,-"));
//        homeModels.add(new HomeModel("5", "Choco Latte", "15.000,-"));
//        homeModels.add(new HomeModel("6", "Americano", "15.000,-"));
//
//        homeAdapter = new HomeAdapter(homeModels);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
//        recyclerView.setLayoutManager(gridLayoutManager);
//        recyclerView.setAdapter(homeAdapter);

    }
}
