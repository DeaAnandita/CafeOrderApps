package com.example.cafeorderapps.Adapter;

import android.app.Dialog;
import android.content.Context;
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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafeorderapps.Model.DrinkModel;
import com.example.cafeorderapps.Model.FoodModel;
import com.example.cafeorderapps.Model.HomeModel;
import com.example.cafeorderapps.R;
import com.example.cafeorderapps.RealmHelper;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder>{

    RealmHelper realmHelper;
    Realm realm;
    private List<HomeModel> dataList;
    private List<DrinkModel> drinkModels;
    Context mContext;
    View viewku;

    public DrinkAdapter(Context mContext, List<HomeModel> dataList, ArrayList<DrinkModel> drinkModels) {
        this.mContext = mContext;
        this.dataList = dataList;
        this.drinkModels = drinkModels;
    }

    @NonNull
    @Override
    public DrinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        viewku = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new DrinkAdapter.DrinkViewHolder(viewku);
    }

    @Override
    public void onBindViewHolder(@NonNull final DrinkViewHolder holder, final int position) {
        Realm.init(holder.itemView.getContext());
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);
        holder.txtHarga.setText(dataList.get(Integer.parseInt(drinkModels.get(position).getPosition())).getHargaMakanan());
        holder.txtNama.setText(dataList.get(Integer.parseInt(drinkModels.get(position).getPosition())).getNamaMakanan());
        holder.doubleClick = dataList.get(Integer.parseInt(drinkModels.get(position).getPosition())).isDoubleClick();
        holder.cardku.setVisibility(View.VISIBLE);
        if (holder.doubleClick){
            holder.ivCheck.setVisibility(View.VISIBLE);
        }else {
            holder.ivCheck.setVisibility(View.INVISIBLE);
        }
        holder.cardku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.doubleClick = dataList.get(Integer.parseInt(drinkModels.get(position).getPosition())).isDoubleClick();
                Log.d("TAG", "onClick: ");
                if (!holder.doubleClick) {
                    holder.ivCheck.setVisibility(View.VISIBLE);
                    realmHelper.updateCheck(dataList.get(Integer.parseInt(drinkModels.get(position).getPosition())).getId(), true);
                    final Dialog d = new Dialog(holder.itemView.getContext());
                    d.setTitle("NumberPicker");
                    d.setContentView(R.layout.number_picker);
                    final NumberPicker np = d.findViewById(R.id.numberPicker1);
                    np.setMaxValue(50);
                    np.setMinValue(0);
                    np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                        @Override
                        public void onValueChange(NumberPicker numberPicker, final int i, final int i2) {

                            Button DialogButton = d.findViewById(R.id.button);
                            DialogButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Log.d("kosong", "onCreateView: " + Integer.parseInt(drinkModels.get(position).getPosition()));

                                    Toast.makeText(holder.itemView.getContext(), Integer.toString(i2), Toast.LENGTH_SHORT).show();
                                    realmHelper.updateJumlah(dataList.get(Integer.parseInt(drinkModels.get(position).getPosition())).getId(), Integer.toString(i2));

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
                            realmHelper.updateCheck(dataList.get(Integer.parseInt(drinkModels.get(position).getPosition())).getId(), false);
                        }
                    });
                    d.show();


                }else {
                    holder.ivCheck.setVisibility(View.INVISIBLE);
                    realmHelper.updateCheck(dataList.get(Integer.parseInt(drinkModels.get(position).getPosition())).getId(), false);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return drinkModels.size();
    }

    class DrinkViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNama, txtHarga;
        private ImageView img, ivCheck;
        boolean doubleClick;
        CardView cardku;

        DrinkViewHolder(View itemView) {
            super(itemView);
            cardku = itemView.findViewById(R.id.cardku);
            txtNama = itemView.findViewById(R.id.txtNama);
            txtHarga = itemView.findViewById(R.id.txtHarga);
            img = itemView.findViewById(R.id.image);
            ivCheck = itemView.findViewById(R.id.ivCheck);
        }
    }

}
