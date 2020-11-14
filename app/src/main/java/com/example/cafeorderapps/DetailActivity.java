package com.example.cafeorderapps;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DetailAdapter detailAdapter;
    ArrayList<DetailModel> DetailModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

//        Toolbar toolbar = findViewById(R.id.toolbarDetail);
//
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Detail Pesanan");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        recyclerView = findViewById(R.id.rvDetail);
//
//        DetailModels = new ArrayList<>();
//        DetailModels.add(new DetailModel("1", "Susu Caramel", "15.000,-"));
//        DetailModels.add(new DetailModel("2","Susu Caramel", "15.000,-"));
//
//        detailAdapter = new DetailAdapter(DetailModels);
//
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DetailActivity.this);
//
//        recyclerView.setLayoutManager(layoutManager);
//
//        recyclerView.setAdapter(detailAdapter);
    }
}