package com.example.cafeorderapps;

<<<<<<< HEAD
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
=======
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
>>>>>>> 6ff4a1340b166c5c883bf38ee8824e66b3920097

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    HomeAdapter homeAdapter;
    ArrayList<HomeModel> homeModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

<<<<<<< HEAD
=======
        recyclerView = findViewById(R.id.list);

        homeModels = new ArrayList<>();
        homeModels.add(new HomeModel("1", "Nama1", "email1@gmail.com"));
        homeModels.add(new HomeModel("2","Nama2", "email2@gmail.com"));
        homeModels.add(new HomeModel("3","Nama3", "email3@gmail.com"));
        homeModels.add(new HomeModel("4","Nama4", "email4@gmail.com"));
        homeModels.add(new HomeModel("5","Nama5", "email5@gmail.com"));
        homeModels.add(new HomeModel("6","Nama6", "email6@gmail.com"));

        homeAdapter = new HomeAdapter(homeModels);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(homeAdapter);
>>>>>>> 6ff4a1340b166c5c883bf38ee8824e66b3920097

    }
}
