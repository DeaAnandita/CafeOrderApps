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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafeorderapps.Adapter.FoodAdapter;
import com.example.cafeorderapps.Model.HomeModel;
import com.example.cafeorderapps.R;

import java.util.ArrayList;
import java.util.List;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class FoodFragment extends Fragment {

    private Realm realm;
    private ArrayList mProdukList;
    RecyclerView recyclerView;
    FoodAdapter foodAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_fragment, container, false);

        recyclerView = view.findViewById(R.id.list_fragment_food);

        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        if (mProdukList == null)
            mProdukList = new ArrayList<>();

        RealmResults<HomeModel> homeModels = realm.where(HomeModel.class).findAll();

        mProdukList.addAll(homeModels);

        Log.d("TAG", "onCreateView: " + homeModels.toString());

        foodAdapter = new FoodAdapter(getContext(), mProdukList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(foodAdapter);

        return view;
    }


}
