package com.example.cafeorderapps.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafeorderapps.Adapter.CategoriAdapter;
import com.example.cafeorderapps.Adapter.HomeAdapter;
import com.example.cafeorderapps.Model.CategoriModel;
import com.example.cafeorderapps.Model.HomeModel;
import com.example.cafeorderapps.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class FoodFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView recyclerView1;
    RecyclerView recyclerView2;
    HomeAdapter homeAdapter;
    CategoriAdapter categoriAdapter;
    ArrayList<HomeModel> homeModels;
    ArrayList<CategoriModel> categoriModels;
    List<HomeModel> searchModels;

    ArrayAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_fragment, container, false);

        setHasOptionsMenu(true);

        searchModels = new ArrayList<>();
        getData();
        adapter = new ArrayAdapter<HomeAdapter>(getActivity(), R.layout.support_simple_spinner_dropdown_item);

        recyclerView = view.findViewById(R.id.list_fragment_food);
        recyclerView1 = view.findViewById(R.id.rvkategori_fragment_food);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        BottomNavigationView navigationView = view.findViewById(R.id.navigationView);
        recyclerView2 = view.findViewById(R.id.list);

        categoriModels = new ArrayList<>();
        categoriModels.add(new CategoriModel("1", "Chicken", false));
        categoriModels.add(new CategoriModel("2", "Noodle", false));
        categoriModels.add(new CategoriModel("3", "Soup", false));
        categoriModels.add(new CategoriModel("4", "Cake", false));
        categoriModels.add(new CategoriModel("5", "Cookie", false));
        categoriModels.add(new CategoriModel("6", "Salad", false));
        categoriModels.add(new CategoriModel("7", "Chicken", false));
        categoriModels.add(new CategoriModel("8", "Noodle", false));
        categoriModels.add(new CategoriModel("9", "Soup", false));
        categoriModels.add(new CategoriModel("10", "Cake", false));
        categoriModels.add(new CategoriModel("11", "Cookie", false));
        categoriModels.add(new CategoriModel("12", "Salad", false));

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

        homeAdapter = new HomeAdapter(getContext(), homeModels);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(homeAdapter);

        return view;
    }

    private void getData() {
        searchModels = new ArrayList<>();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String nextText) {
                try {
                    recyclerView.setAdapter(homeAdapter);
                    homeAdapter.getFilter().filter(nextText);
                    for (HomeModel model : homeModels) {
                        Log.d("", ""+model.getEmail());
                    }
                } catch (Exception e) {
                    Log.d("error", "" + e.toString());
                }
                return false;
            }
        });
    }
}
