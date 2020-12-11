package com.example.cafeorderapps;

import android.util.Log;

import com.example.cafeorderapps.Model.HomeModel;

import io.realm.Realm;

public class RealmHelper {

    Realm realm;

    public RealmHelper(Realm realm){

        this.realm = realm;

    }

    public void updateCheck(final String id, final boolean doubleClick){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                HomeModel model = realm.where(HomeModel.class)
                        .equalTo("id", id)
                        .findFirst();
                model.setDoubleClick(doubleClick);
            }

        }, new Realm.Transaction.OnSuccess(){

            @Override
            public void onSuccess() {

                Log.d("ppp", "onSuccess: Update successfully");

            }

        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {

                error.printStackTrace();

            }
        });
    }

    public void updateJumlah(final String id, final String jumlah){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                HomeModel model = realm.where(HomeModel.class)
                        .equalTo("id", id)
                        .findFirst();
                model.setJumlah(jumlah);
            }

        }, new Realm.Transaction.OnSuccess(){

            @Override
            public void onSuccess() {

                Log.d("ppp", "onSuccess: Update successfully");

            }

        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {

                error.printStackTrace();

            }
        });
    }

}
