package com.example.cafeorderapps.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.cafeorderapps.Adapter.DetailAdapter;
import com.example.cafeorderapps.Adapter.FoodAdapter;
import com.example.cafeorderapps.BaseUrl;
import com.example.cafeorderapps.GetProduk;
import com.example.cafeorderapps.HomeActivity;
import com.example.cafeorderapps.LoginActivity;
import com.example.cafeorderapps.Model.HomeModel;
import com.example.cafeorderapps.R;
import com.example.cafeorderapps.RealmHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class CartFragment extends Fragment {

    RealmHelper realmHelper;
    private Realm realm;
    private List<HomeModel> mProdukList;
    RecyclerView recyclerView;
    DetailAdapter detailAdapter;
    Button btnPesan;
    ProgressDialog progressDialog;
    EditText edAtasNama, edNote;

    //Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.cart_fragment, container, false);

//        toolbar = view.findViewById(R.id.toolbarDetail);
//
//        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Detail Pesanan");
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = view.findViewById(R.id.rvDetail);
        btnPesan = view.findViewById(R.id.btnPesan);
        edAtasNama = view.findViewById(R.id.edAtasNama);
        edNote = view.findViewById(R.id.edNote);

        progressDialog = new ProgressDialog(view.getContext());

        recyclerView.setNestedScrollingEnabled(false);

        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        if (mProdukList == null)
            mProdukList = new ArrayList<>();

        RealmResults<HomeModel> homeModels = realm.where(HomeModel.class).findAll();
        mProdukList.addAll(homeModels);

        Log.d("TAG", "onCreateView: " + homeModels.toString());

        detailAdapter = new DetailAdapter(getContext(), mProdukList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(detailAdapter);

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        final String formattedDate = df.format(c);

        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int total = 0;
                for (int i = 0; i < mProdukList.size(); i++) {
                    if (mProdukList.get(i).isDoubleClick()){
                        int subTotal = Integer.parseInt(mProdukList.get(i).getJumlah()) * Integer.parseInt(mProdukList.get(i).getHargaMakanan());
                        total += subTotal;
                    }
                }
                AndroidNetworking.post(BaseUrl.url + "insertnota.php")
                        .addBodyParameter("namaCustomer", edAtasNama.getText().toString())
                        .addBodyParameter("tanggalNota", formattedDate)
                        .addBodyParameter("nohpCustomer", "085")
                        .addBodyParameter("note", edNote.getText().toString())
                        .addBodyParameter("kodeNota", "-")
                        .addBodyParameter("totalHarga", String.valueOf(total))
                        .addBodyParameter("statusOrder", "Tunggu")
                        .setPriority(Priority.LOW)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONObject hasil = response.getJSONObject("hasil");
                                    boolean sukses = hasil.getBoolean("respon");
                                    String id = hasil.getString("id");
                                    Log.d("PAYLOAD", "onResponse: " + hasil);
                                    if (sukses) {
                                        Log.d("TAG", "onResponse: masuk");
                                        for (int i = 0; i < mProdukList.size(); i++) {
                                            realmHelper.updateCheck(mProdukList.get(i).getId(), false);

                                            if (mProdukList.get(i).isDoubleClick()){
                                                int subTotal = Integer.parseInt(mProdukList.get(i).getJumlah()) * Integer.parseInt(mProdukList.get(i).getHargaMakanan());
                                                AndroidNetworking.post(BaseUrl.url + "insertdetailnota.php")
                                                        .addBodyParameter("kodeNota", id)
                                                        .addBodyParameter("kodeMakanan", mProdukList.get(i).getKodeMakanan())
                                                        .addBodyParameter("jumlahItem", mProdukList.get(i).getJumlah())
                                                        .addBodyParameter("subTotal", String.valueOf(subTotal))
                                                        .addBodyParameter("hargaSatuan", mProdukList.get(i).getHargaMakanan())
                                                        .setPriority(Priority.LOW)
                                                        .build()
                                                        .getAsJSONObject(new JSONObjectRequestListener() {
                                                            @Override
                                                            public void onResponse(JSONObject response) {
                                                                try {
                                                                    JSONObject hasil = response.getJSONObject("hasil");
                                                                    boolean sukses = hasil.getBoolean("respon");
                                                                    Log.d("PAYLOAD", "onResponse: " + hasil);
                                                                    if (sukses) {
                                                                        Log.d("TAG", "onResponse: masuk");
                                                                    }
                                                                } catch (JSONException e) {
                                                                    e.printStackTrace();
                                                                }
                                                            }

                                                            @Override
                                                            public void onError(ANError anError) {

                                                            }
                                                        });
                                            }

                                            Intent intent = new Intent(view.getContext(), HomeActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                            getActivity().finish();
                                        }
                                    }else{
                                        Log.e("TAG", "onResponse: gagal" );
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(ANError anError) {

                            }
                        });
                Log.e("test", "onClick: " + total);
            }
        });

        return view;
    }
}
