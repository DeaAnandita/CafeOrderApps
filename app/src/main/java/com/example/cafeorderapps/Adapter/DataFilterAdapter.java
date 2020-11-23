package com.example.cafeorderapps.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafeorderapps.Model.HomeModel;
import com.example.cafeorderapps.R;

import java.util.ArrayList;

public class DataFilterAdapter extends RecyclerView.Adapter<DataFilterAdapter.ViewHolder>{

    private static ArrayList<HomeModel> arrayList1;
    private ArrayList<HomeModel> arrayList;

    DataFilterAdapter(ArrayList<HomeModel> arrayList){
        this.arrayList = arrayList;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView Nama, Harga;
        ImageView img;
        CardView cardku;

        ViewHolder(View itemView) {
            super(itemView);
            cardku = itemView.findViewById(R.id.cardku);
            Nama = itemView.findViewById(R.id.txtnama);
            Harga = itemView.findViewById(R.id.txtemail);
            img = itemView.findViewById(R.id.image);
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

    public static void setFilter(ArrayList<HomeModel> filterList){
        arrayList1 = new ArrayList<>();
        arrayList1.addAll(filterList);
//        notifyDataSetChanged();
    }
}
