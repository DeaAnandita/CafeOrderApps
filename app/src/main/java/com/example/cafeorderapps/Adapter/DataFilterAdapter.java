package com.example.cafeorderapps.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafeorderapps.Model.HomeModel;
import com.example.cafeorderapps.R;

import java.util.ArrayList;
import java.util.List;

public class DataFilterAdapter extends RecyclerView.Adapter<DataFilterAdapter.ViewHolder> implements Filterable {

    private static ArrayList<HomeModel> arrayList1;
    private ArrayList<HomeModel> dataList;
    private ArrayList<HomeModel> dataListFull;

    DataFilterAdapter(ArrayList<HomeModel> arrayList){
        this.dataList = arrayList;
        dataListFull = new ArrayList<>(arrayList);
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
        holder.Nama.setText(dataList.get(position).getNama());
        holder.Harga.setText(dataList.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public Filter getFilter() {
        return dataListFilter;
    }

    private Filter dataListFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<HomeModel> filteredList = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(dataListFull);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (HomeModel item : dataListFull) {
                    if (item.getNama().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            dataList.clear();
            dataList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

}
