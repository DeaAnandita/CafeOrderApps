package com.example.cafeorderapps;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Collections;

public class SearchBar extends AppCompatActivity {

    private String[] mahasiswa = {"Wildan","Taufan","Adibil;","Hari","Adam",
            "Hermawan","Indra","Widi","Anisa","Hani","Ujang","Saepuloh","Dirham","Rizal","Risma",
            "Wulandari","Widi Utari","William"};

    private ArrayList data;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView listView = findViewById(R.id.list);
        data = new ArrayList<>();
        getData();
        adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, data);
        listView.setAdapter(adapter);
    }

    private void getData(){
        Collections.addAll(data, mahasiswa);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String nextText) {
                adapter.getFilter().filter(nextText);
                return false;
            }
        });
        return true;

    }
}