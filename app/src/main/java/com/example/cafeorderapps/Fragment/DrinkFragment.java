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
import com.example.cafeorderapps.Model.HomeModel;
import com.example.cafeorderapps.R;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class DrinkFragment extends Fragment {

    private Realm realm;
    private ArrayList mProdukList;
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

        drinkAdapter = new DrinkAdapter(getContext(), mProdukList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(drinkAdapter);

        return view;
    }

}
