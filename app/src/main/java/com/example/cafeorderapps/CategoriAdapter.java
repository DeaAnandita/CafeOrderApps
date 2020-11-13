package com.example.cafeorderapps;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoriAdapter extends RecyclerView.Adapter<CategoriAdapter.HomeViewHolder>{

    private ArrayList<CategoriModel> dataList;
    View viewku;

    public CategoriAdapter(ArrayList<CategoriModel> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        viewku = layoutInflater.inflate(R.layout.listitem_categori, parent, false);
        return new HomeViewHolder(viewku);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.txtnama.setText(dataList.get(position).getTxtNamaCatg());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class HomeViewHolder extends RecyclerView.ViewHolder{
        private TextView txtnama;
        private ImageView img;

        HomeViewHolder(View itemView) {
            super(itemView);
            txtnama = itemView.findViewById(R.id.txtNamaCatg);
            img = itemView.findViewById(R.id.img);

        }
    }

}
