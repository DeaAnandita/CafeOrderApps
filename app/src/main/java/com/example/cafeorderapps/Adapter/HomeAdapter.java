package com.example.cafeorderapps.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafeorderapps.Model.HomeModel;
import com.example.cafeorderapps.R;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> implements Filterable {

    private List<HomeModel> dataList;
    private List<HomeModel> dataListFull;
    Context mContext;
    View viewku;

    public HomeAdapter(Context mContext, List<HomeModel> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
        dataListFull = new ArrayList<>(dataList);
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        viewku = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new HomeViewHolder(viewku);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.txtnama.setText(dataList.get(position).getNama());
        holder.txtEmail.setText(dataList.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    class HomeViewHolder extends RecyclerView.ViewHolder{
        private TextView txtEmail, txtnama;
        private ImageView img;
        CardView cardku;

        HomeViewHolder(View itemView) {
            super(itemView);
            cardku = itemView.findViewById(R.id.cardku);
            txtEmail = itemView.findViewById(R.id.txtemail);
            txtnama = itemView.findViewById(R.id.txtnama);
            img = itemView.findViewById(R.id.image);

        }
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
                    if (item.getEmail().toLowerCase().contains(filterPattern)) {
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
