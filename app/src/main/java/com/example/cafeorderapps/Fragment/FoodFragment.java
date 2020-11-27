package com.example.cafeorderapps.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafeorderapps.Adapter.CategoriAdapter;
import com.example.cafeorderapps.Adapter.HomeAdapter;
import com.example.cafeorderapps.Model.CategoriModel;
import com.example.cafeorderapps.Model.HomeModel;
import com.example.cafeorderapps.R;

import java.util.ArrayList;

public class FoodFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView recyclerView1;
    HomeAdapter homeAdapter;
    CategoriAdapter categoriAdapter;
    ArrayList<HomeModel> homeModels;
    ArrayList<CategoriModel> categoriModels;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_fragment, container, false);

        recyclerView = view.findViewById(R.id.list_fragment_food);
        recyclerView1 = view.findViewById(R.id.rvkategori_fragment_food);

        categoriModels = new ArrayList<>();
        categoriModels.add(new CategoriModel("1", "Chicken", false));
        categoriModels.add(new CategoriModel("2", "Noodle", false));
        categoriModels.add(new CategoriModel("3", "Soup", false));
        categoriModels.add(new CategoriModel("4", "Cake", false));
        categoriModels.add(new CategoriModel("5", "Cookie", false));
        categoriModels.add(new CategoriModel("6", "Salad", false));
        categoriAdapter = new CategoriAdapter(categoriModels);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(layoutManager);
        recyclerView1.setAdapter(categoriAdapter);

        homeModels = new ArrayList<>();
        homeModels.add(new HomeModel("1", "Chicken Strips", "16.000,-", false));
        homeModels.add(new HomeModel("2", "Spaghetti Supreme", "30.000,-", false));
        homeModels.add(new HomeModel("3", "Cream Soup", "25.000,-", false));
        homeModels.add(new HomeModel("4", "Pudding", "30.000,-", false));
        homeModels.add(new HomeModel("5", "Cookie Crumb", "23.000,-", false));
        homeModels.add(new HomeModel("6", "Salad Deluxe", "32.000,-", false));

        homeAdapter = new HomeAdapter(homeModels);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(homeAdapter);

        return view;
    }
}
