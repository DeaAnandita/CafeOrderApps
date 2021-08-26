package com.example.cafeorderapps;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.cafeorderapps.Model.HomeModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class GetProduk extends AppCompatActivity {

    Button btnDownload;
    private ArrayList mProdukList = new ArrayList<HomeModel>();
    private Realm realm;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_produk);

        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        progressDialog = new ProgressDialog(this);

        btnDownload = findViewById(R.id.btnDownload);
        mProdukList.clear();

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setTitle("Downloading In...");
                progressDialog.show();
                AndroidNetworking.get(BaseUrl.url + "getproduk.php")
                        .setTag("test")
                        .setPriority(Priority.LOW)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray data = response.getJSONArray("PAYLOAD");
                                    for (int i = 0; i < data.length(); i++) {
                                        HomeModel model = new HomeModel();
                                        JSONObject object = data.getJSONObject(i);
                                        model.setId(object.getString("id"));
                                        model.setKodeMakanan(object.getString("kodeMakanan"));
                                        model.setNamaMakanan(object.getString("namaMakanan"));
                                        model.setJenisMakanan(object.getString("jenisMakanan"));
                                        model.setHargaMakanan(object.getString("hargaMakanan"));
                                        model.setAvatar(object.getString("avatar"));
                                        model.setDoubleClick(false);
                                        mProdukList.add(model);
                                    }
                                    Intent intent = new Intent(GetProduk.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                    realm.executeTransactionAsync(new Realm.Transaction() {
                                        @Override
                                        public void execute(Realm realma) {
                                            realma.deleteAll();
                                            realma.insertOrUpdate(mProdukList);
                                        }
                                    }, new Realm.Transaction.OnSuccess() {
                                        @Override
                                        public void onSuccess() {
                                            Log.e("RBA", "Realm onSuccess: success insert");
                                            RealmResults<HomeModel> homeModels = realm.where(HomeModel.class).findAll();
                                            Log.d("RBA", "Realm Size From Api : " + homeModels.size());
                                        }
                                    }, new Realm.Transaction.OnError() {
                                        @Override
                                        public void onError(Throwable error) {
                                            Log.e("RBA", "Realm onError: " + error.getLocalizedMessage());
                                        }
                                    });
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(ANError anError) {

                            }
                        });
            }
        });

    }
}