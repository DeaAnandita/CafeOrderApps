package com.example.cafeorderapps.Adapter;

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
import com.example.cafeorderapps.PrefConfig;
import com.example.cafeorderapps.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.content.ContentValues.TAG;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder>{

    private ArrayList<HomeModel> dataList;
    private List<DetailModel> modelList = new ArrayList<>();
    View viewku;

    public HomeAdapter(ArrayList<HomeModel> dataList) {
        this.dataList = dataList;
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
                    np.setMaxValue(100);
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
                                    DetailModel taskModel = new DetailModel("a", dataList.get(position).getNama(), dataList.get(position).getEmail(), Integer.toString(i2));
                                    modelList.add(taskModel);
                                    PrefConfig.writeListInPref(holder.itemView.getContext(), modelList);
                                    Collections.reverse(modelList);
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

}
