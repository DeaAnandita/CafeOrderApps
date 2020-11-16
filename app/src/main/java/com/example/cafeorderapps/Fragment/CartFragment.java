package com.example.cafeorderapps.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafeorderapps.Adapter.DetailAdapter;
import com.example.cafeorderapps.Model.DetailModel;
import com.example.cafeorderapps.R;

import java.util.ArrayList;

public class CartFragment extends Fragment {

    RecyclerView recyclerView;
    DetailAdapter detailAdapter;
    ArrayList<DetailModel> DetailModels;
    Toolbar toolbar;

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

        DetailModels = new ArrayList<>();
        DetailModels.add(new DetailModel("1", "Susu Caramel", "15.000,-"));
        DetailModels.add(new DetailModel("2", "Susu Caramel", "15.000,-"));

        detailAdapter = new DetailAdapter(DetailModels);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(detailAdapter);

        return view;
    }
}
