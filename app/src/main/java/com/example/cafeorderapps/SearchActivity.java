package com.example.cafeorderapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.cafeorderapps.Adapter.SearchAdapter;
import com.example.cafeorderapps.Model.HomeModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class SearchActivity extends AppCompatActivity {

    private Realm realm;
    private List<HomeModel> mProdukList;
    RecyclerView recyclerView;
    SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.rvListSearch);

        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        if (mProdukList == null)
            mProdukList = new ArrayList<>();

        RealmResults<HomeModel> homeModels = realm.where(HomeModel.class).findAll();

        mProdukList.addAll(homeModels);

        searchAdapter = new SearchAdapter(mProdukList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(searchAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.mSearchMenu);
        final SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mProdukList.clear();
                RealmResults<HomeModel> produkModel = realm.where(HomeModel.class).contains("namaMakanan",query, Case.INSENSITIVE).findAll();
                mProdukList.addAll(produkModel);
                searchAdapter.notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    mProdukList.clear();
                    RealmResults<HomeModel> produkModel = realm.where(HomeModel.class).findAll();
                    mProdukList.addAll(produkModel);
                    searchAdapter.notifyDataSetChanged();
                }
                return true;
            }
        });
        return true;
    }
}