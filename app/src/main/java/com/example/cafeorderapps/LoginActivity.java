package com.example.cafeorderapps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

import static androidx.constraintlayout.widget.Constraints.TAG;

public class LoginActivity extends AppCompatActivity {

    EditText edUser,edpass;
    Button btnLogin;
    SharedPreferences sp;
    ProgressDialog progressDialog;
    private ArrayList mProdukList = new ArrayList<HomeModel>();
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUser = findViewById(R.id.edUsername);
        edpass = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);

        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        progressDialog = new ProgressDialog(this);

        sp = getSharedPreferences("login",MODE_PRIVATE);
        sp.edit().putString("logged", sp.getString("logged", "missing")).apply();

        String user = sp.getString("logged", "missing");

        if(user.equals("user")){
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = edUser.getText().toString();
                String password = edpass.getText().toString().trim();
                progressDialog.setTitle("Logging In...");
                progressDialog.show();
                AndroidNetworking.post(BaseUrl.url + "login.php")
                        .addBodyParameter("username", username)
                        .addBodyParameter("password", password)
                        .setPriority(Priority.LOW)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("hasil", "onResponse: ");
                                try {
                                    JSONObject PAYLOAD = response.getJSONObject("PAYLOAD");
                                    boolean sukses = PAYLOAD.getBoolean("respon");
                                    Log.d("PAYLOAD", "onResponse: " + PAYLOAD);
                                    if (sukses) {
                                        sp.edit().putString("logged","user").apply();
                                        mProdukList.clear();
                                        fetchDataProdukAPI();
                                        progressDialog.dismiss();
                                    } else {
                                        Toast.makeText(LoginActivity.this, "gagal", Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(ANError anError) {
                                progressDialog.dismiss();
                                Log.d("TAG", "onError: " + anError.getErrorDetail());
                                Log.d("TAG", "onError: " + anError.getErrorBody());
                                Log.d("TAG", "onError: " + anError.getErrorCode());
                                Log.d("TAG", "onError: " + anError.getResponse());
                            }
                        });

            }
        });
    }

    private void fetchDataProdukAPI() {

        AndroidNetworking.get("http://192.168.6.188/apibajawa/getproduk.php")
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
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                            realm.executeTransactionAsync(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realma) {
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
}
