package com.icmi.ecommerceadmin.Fragments.OrderHistory;

import com.icmi.ecommerceadmin.Model.Product;

public interface Contractor {
    interface View {
        void onSuccess();

        void onError();

        void showLoading();

        void hideLoading();
    }

    interface Presenter {

        void addToHistory(Product product);

    }
}
