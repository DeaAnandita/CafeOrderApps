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

public class DrinkFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    RecyclerView recyclerView1;
    HomeAdapter homeAdapter;
    CategoriAdapter categoriAdapter;
    ArrayList<HomeModel> homeModels;
    ArrayList<CategoriModel> categoriModels;
    List<HomeModel> searchModels;

    ArrayAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.drink_fragment, container, false);
        setHasOptionsMenu(true);

        searchModels = new ArrayList<>();
        getData();
        adapter = new ArrayAdapter<HomeAdapter>(getActivity(), R.layout.support_simple_spinner_dropdown_item);

        recyclerView = view.findViewById(R.id.list_fragment);
        recyclerView1 = view.findViewById(R.id.rvkategori_fragment);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        BottomNavigationView navigationView = view.findViewById(R.id.navigationView);
        recyclerView2 = view.findViewById(R.id.list);

        categoriModels = new ArrayList<>();
        categoriModels.add(new CategoriModel("1", "Mochaccino",false));
        categoriModels.add(new CategoriModel("2", "Susu",false));
        categoriModels.add(new CategoriModel("3", "Espresso",false));
        categoriModels.add(new CategoriModel("4", "Macchiato",false));
        categoriModels.add(new CategoriModel("5", "Choco",false));
        categoriModels.add(new CategoriModel("6", "Americano",false));
        categoriModels.add(new CategoriModel("7", "Mochaccino",false));
        categoriModels.add(new CategoriModel("8", "Susu",false));
        categoriModels.add(new CategoriModel("9", "Espresso",false));
        categoriModels.add(new CategoriModel("10", "Macchiato",false));
        categoriModels.add(new CategoriModel("11", "Choco",false));

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
        homeModels.add(new HomeModel("4", "Macchiato", "15.000,-",false));
        homeModels.add(new HomeModel("5", "Choco Latte", "15.000,-",false));
        homeModels.add(new HomeModel("6", "Americano", "15.000,-",false));
        homeModels.add(new HomeModel("7", "Mochaccino", "15.000,-",false));
        homeModels.add(new HomeModel("8", "Susu Caramel", "15.000,-",false));
        homeModels.add(new HomeModel("9", "Espresso", "15.000,-",false));
        homeModels.add(new HomeModel("10", "Macchiato", "15.000,-",false));
        homeModels.add(new HomeModel("11", "Choco Latte", "15.000,-",false));
        homeModels.add(new HomeModel("12", "Americano", "15.000,-",false));

        homeModels.add(new HomeModel("1", "Mochaccino", "15.000,-", false));
        homeModels.add(new HomeModel("2", "Susu Caramel", "15.000,-", false));
        homeModels.add(new HomeModel("3", "Espresso", "15.000,-", false));
        homeModels.add(new HomeModel("4", "Macchiato", "15.000,-", false));
        homeModels.add(new HomeModel("5", "Choco Latte", "15.000,-", false));
        homeModels.add(new HomeModel("6", "Americano", "15.000,-", false));


        homeAdapter = new HomeAdapter(getContext(), homeModels);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(homeAdapter);

        return view;
    }
    private void getData() {
        searchModels = new ArrayList<>();

//        AndroidNetworking.post("http://192.168.6.114/RentalMobil/ViewData.php")
//                .addBodyParameter("roleuser", "2")
//                .setTag("test")
//                .setPriority(Priority.MEDIUM)
//                .build()
//                .getAsJSONObject(new JSONObjectRequestListener() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            JSONArray data = response.getJSONArray("result");
//
//                            for (int i = 0; i < data.length(); i++) {
//
//                                HomeModel model = new HomeModel();
//                                JSONObject object = data.getJSONObject(i);
//                                model.setId(object.getString("id"));
//                                model.setEmail(object.getString("email"));
//                                model.setNama(object.getString("nama"));
//                                homeModels.add(model);
//
//                            }
//
//                            homeAdapter = new HomeAdapter(getContext(), homeModels);
//
//                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
//
//                            recyclerView.setLayoutManager(layoutManager);
//
//                            recyclerView.setAdapter(homeAdapter);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//                        Log.d("dea", "onResponse: " + anError.toString());
//                        Log.d("dea", "onResponse: " + anError.getErrorBody());
//                        Log.d("dea", "onResponse: " + anError.getErrorCode());
//                        Log.d("dea", "onResponse: " + anError.getErrorDetail());
//                    }
//                });
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
