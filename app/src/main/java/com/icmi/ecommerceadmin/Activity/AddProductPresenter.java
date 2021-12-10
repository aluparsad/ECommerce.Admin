package com.icmi.ecommerceadmin.Activity;


import android.util.Log;

import com.icmi.ecommerceadmin.Model.Product;
import com.icmi.ecommerceadmin.Utils.Listener;
import com.icmi.ecommerceadmin.Utils.database;

import java.util.List;

public class AddProductPresenter implements AddProductContract.Presenter {
    private AddProductContract.view view;

    private AddProductPresenter() {
    }

    AddProductPresenter(AddProductContract.view view) {
        this.view = view;
    }

    @Override
    public void RegisterProduct(Product newProduct, List<String> images) {
        if (newProduct != null) {
            database d = new database(new Listener.OnComplete() {
                @Override
                public void onSuccessListener() {
                    Log.d("UT", "onSuccessListener: Uploaded Product" );
                    view.OnSuccess();
                }
                @Override
                public void onFailureListener() {
                    view.OnFailure("Failed to register Product");
                }
            });

            d.addProduct(newProduct, images);
        }
    }


}
