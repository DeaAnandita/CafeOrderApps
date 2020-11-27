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
        View view = inflater.inflate(R.layout.drink_fragment, container, false);

        recyclerView = view.findViewById(R.id.list_fragment);
        recyclerView1 = view.findViewById(R.id.rvkategori_fragment);

        categoriModels = new ArrayList<>();
        categoriModels.add(new CategoriModel("1", "Mochaccino", false));
        categoriModels.add(new CategoriModel("2", "Susu", false));
        categoriModels.add(new CategoriModel("3", "Espresso", false));
        categoriModels.add(new CategoriModel("4", "Macchiato", false));
        categoriModels.add(new CategoriModel("5", "Choco", false));
        categoriModels.add(new CategoriModel("6", "Americano", false));
        categoriAdapter = new CategoriAdapter(categoriModels);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(layoutManager);
        recyclerView1.setAdapter(categoriAdapter);

        homeModels = new ArrayList<>();
        homeModels.add(new HomeModel("1", "Mochaccino", "15.000,-", false));
        homeModels.add(new HomeModel("2", "Susu Caramel", "15.000,-", false));
        homeModels.add(new HomeModel("3", "Espresso", "15.000,-", false));
        homeModels.add(new HomeModel("4", "Macchiato", "15.000,-", false));
        homeModels.add(new HomeModel("5", "Choco Latte", "15.000,-", false));
        homeModels.add(new HomeModel("6", "Americano", "15.000,-", false));

        homeAdapter = new HomeAdapter(homeModels);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(homeAdapter);

        return view;
    }
}
