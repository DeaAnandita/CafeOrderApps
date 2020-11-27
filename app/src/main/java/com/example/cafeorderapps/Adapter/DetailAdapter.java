package com.example.cafeorderapps.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafeorderapps.Model.DetailModel;
import com.example.cafeorderapps.R;

import java.util.ArrayList;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.HomeViewHolder>{

    private ArrayList<DetailModel> dataList;
    View viewku;

    public DetailAdapter(ArrayList<DetailModel> dataList) {
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
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.txtnama.setText(dataList.get(position).getNamaD());
        holder.txtHarga.setText(dataList.get(position).getHargaD());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class HomeViewHolder extends RecyclerView.ViewHolder{
        private TextView txtHarga, txtnama;
        private ImageView img;
        CardView cardku;

        HomeViewHolder(View itemView) {
            super(itemView);
            cardku = itemView.findViewById(R.id.cardku_D);
            txtnama = itemView.findViewById(R.id.txtnamaD);
            txtHarga = itemView.findViewById(R.id.txthargaD);
            img = itemView.findViewById(R.id.imgD);

        }
    }

}
