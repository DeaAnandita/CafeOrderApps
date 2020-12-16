package com.example.cafeorderapps.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafeorderapps.Adapter.DrinkAdapter;
import com.example.cafeorderapps.Model.DrinkModel;
import com.example.cafeorderapps.Model.FoodModel;
import com.example.cafeorderapps.Model.HomeModel;
import com.example.cafeorderapps.R;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class DrinkFragment extends Fragment {

    private Realm realm;
    private List<HomeModel> mProdukList;
    private ArrayList<DrinkModel> drinkModels;
    RecyclerView recyclerView;
    DrinkAdapter drinkAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.drink_fragment, container, false);

        recyclerView = view.findViewById(R.id.list_fragment);

        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        if (mProdukList == null)
            mProdukList = new ArrayList<>();

        RealmResults<HomeModel> homeModels = realm.where(HomeModel.class).findAll();
        mProdukList.addAll(homeModels);

        Log.d("TAG", "onCreateView: " + homeModels.toString());

        drinkModels = new ArrayList<>();
        for (int i = 0; i < mProdukList.size(); i++) {
            if (mProdukList.get(i).getJenisMakanan().equals("1")){
                drinkModels.add(new DrinkModel(mProdukList.get(i).getId(), String.valueOf(i)));
            }
        }

        drinkAdapter = new DrinkAdapter(getContext(), mProdukList, drinkModels);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(drinkAdapter);

        return view;
    }

}
