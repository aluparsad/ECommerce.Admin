package com.icmi.ecommerceadmin.Utils;

import com.icmi.ecommerceadmin.Model.Product;

import java.util.List;

public interface Listener {
    interface OnComplete {
        void onSuccessListener();

        void onFailureListener();
    }

    interface ResultListener{
        void getResult(Product product, List<String> images);
    }
}
