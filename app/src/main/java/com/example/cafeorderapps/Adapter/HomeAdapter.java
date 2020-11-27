package com.example.cafeorderapps.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafeorderapps.Model.DetailModel;
import com.example.cafeorderapps.Model.HomeModel;
import com.example.cafeorderapps.R;

import java.util.ArrayList;
import java.util.List;
import static android.content.ContentValues.TAG;

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
    public void onBindViewHolder(@NonNull final HomeViewHolder holder, final int position) {
        holder.txtnama.setText(dataList.get(position).getNama());
        holder.txtEmail.setText(dataList.get(position).getEmail());
        holder.cardku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.doubleClick = dataList.get(position).isDoubleClick();
                if (!holder.doubleClick) {
                    holder.ivCheck.setVisibility(View.VISIBLE);
                    dataList.get(position).setDoubleClick(true);
                    final Dialog d = new Dialog(holder.itemView.getContext());
                    d.setTitle("NumberPicker");
                    d.setContentView(R.layout.number_picker);
                    final NumberPicker np = d.findViewById(R.id.numberPicker1);
                    np.setMaxValue(50);
                    np.setMinValue(0);
                    np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                        @Override
                        public void onValueChange(NumberPicker numberPicker, final int i, final int i2) {

//                            Toast.makeText(holder.itemView.getContext(), "Value was: " + Integer.toString(i) + " is now: " + Integer.toString(i2), Toast.LENGTH_SHORT).show();
                            Button DialogButton = d.findViewById(R.id.button);
                            DialogButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(holder.itemView.getContext(), Integer.toString(i2), Toast.LENGTH_SHORT).show();
                                    d.dismiss();
                                }
                            });
                        }
                    });
                    Button btnBack = d.findViewById(R.id.btn);
                    btnBack.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            d.dismiss();
                            holder.ivCheck.setVisibility(View.INVISIBLE);
                            dataList.get(position).setDoubleClick(false);
                        }
                    });
                    d.show();


                }else {
                    holder.ivCheck.setVisibility(View.INVISIBLE);
                    dataList.get(position).setDoubleClick(false);
                }
                Log.d("mbuh", "onClick: " + holder.doubleClick);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    class HomeViewHolder extends RecyclerView.ViewHolder{
        private TextView txtEmail, txtnama;
        private ImageView img, ivCheck;
        boolean doubleClick;
        CardView cardku;

        HomeViewHolder(View itemView) {
            super(itemView);
            cardku = itemView.findViewById(R.id.cardku);
            txtEmail = itemView.findViewById(R.id.txtemail);
            txtnama = itemView.findViewById(R.id.txtnama);
            img = itemView.findViewById(R.id.image);
            ivCheck = itemView.findViewById(R.id.ivCheck);
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
