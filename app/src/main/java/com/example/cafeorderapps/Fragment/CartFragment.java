package com.example.cafeorderapps.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafeorderapps.Adapter.DetailAdapter;
import com.example.cafeorderapps.Adapter.FoodAdapter;
import com.example.cafeorderapps.Model.HomeModel;
import com.example.cafeorderapps.R;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class CartFragment extends Fragment {

    private Realm realm;
    private ArrayList mProdukList;
    RecyclerView recyclerView;
    DetailAdapter detailAdapter;
    Button btnPesan;

    //Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart_fragment, container, false);

//        toolbar = view.findViewById(R.id.toolbarDetail);
//
//        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Detail Pesanan");
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = view.findViewById(R.id.rvDetail);
        btnPesan = view.findViewById(R.id.btnPesan);

        recyclerView.setNestedScrollingEnabled(false);

        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        if (mProdukList == null)
            mProdukList = new ArrayList<>();

        RealmResults<HomeModel> homeModels = realm.where(HomeModel.class).findAll();
        mProdukList.addAll(homeModels);

        Log.d("TAG", "onCreateView: " + homeModels.toString());

        detailAdapter = new DetailAdapter(getContext(), mProdukList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(detailAdapter);

        return view;
    }
}
