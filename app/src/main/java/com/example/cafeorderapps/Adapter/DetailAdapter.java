package com.example.cafeorderapps.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafeorderapps.Model.HomeModel;
import com.example.cafeorderapps.R;
import com.example.cafeorderapps.RealmHelper;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.HomeViewHolder>{

    RealmHelper realmHelper;
    Realm realm;
    private List<HomeModel> dataList;
    View viewku;
    Context mContext;

    public DetailAdapter(Context mContext, ArrayList<HomeModel> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        viewku = layoutInflater.inflate(R.layout.listitem_detail, parent, false);
        return new HomeViewHolder(viewku);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeViewHolder holder, final int position) {
        Realm.init(holder.itemView.getContext());
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);
        if (dataList.get(position).isDoubleClick()){
            holder.txtnama.setText(dataList.get(position).getNamaMakanan());
            holder.txtHarga.setText(dataList.get(position).getHargaMakanan());
            holder.cardku.setVisibility(View.VISIBLE);
            holder.imggD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    realmHelper.updateCheck(dataList.get(position).getId(), false);
                    holder.cardku.setVisibility(View.GONE);
                }
            });
        }else {
            holder.cardku.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class HomeViewHolder extends RecyclerView.ViewHolder{
        private TextView txtHarga, txtnama;
        private ImageView img, imggD;
        CardView cardku;

        HomeViewHolder(View itemView) {
            super(itemView);
            cardku = itemView.findViewById(R.id.cardku_D);
            txtnama = itemView.findViewById(R.id.txtnamaD);
            txtHarga = itemView.findViewById(R.id.txthargaD);
            img = itemView.findViewById(R.id.imgD);
            imggD = itemView.findViewById(R.id.imggD);

        }
    }

}
