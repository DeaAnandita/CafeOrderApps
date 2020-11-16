package com.example.cafeorderapps.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cafeorderapps.Model.HomeModel;
import com.example.cafeorderapps.R;

import java.util.ArrayList;

public class DataFilterAdapter extends RecyclerView.Adapter<DataFilterAdapter.ViewHolder>{

    private ArrayList<HomeModel> arrayList;

    DataFilterAdapter(ArrayList<HomeModel> arrayList){
        this.arrayList = arrayList;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView Nama, Harga;

        ViewHolder(View itemView) {
            super(itemView);

            Nama = itemView.findViewById(R.id.txtnama);
            Harga = itemView.findViewById(R.id.txtemail);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.Nama.setText(arrayList.get(position).getNama());
        holder.Harga.setText(arrayList.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    void setFilter(ArrayList<HomeModel> filterList){
        arrayList = new ArrayList<>();
        arrayList.addAll(filterList);
        notifyDataSetChanged();
    }
}
