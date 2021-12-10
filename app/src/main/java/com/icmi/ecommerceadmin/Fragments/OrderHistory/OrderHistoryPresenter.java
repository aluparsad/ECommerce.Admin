package com.icmi.ecommerceadmin.Fragments.OrderHistory;

import com.icmi.ecommerceadmin.Model.Order;
import com.icmi.ecommerceadmin.Model.Product;
import com.icmi.ecommerceadmin.Utils.Constants;
import com.icmi.ecommerceadmin.Utils.Listener;
import com.icmi.ecommerceadmin.Utils.database;

public class OrderHistoryPresenter implements Contractor.Presenter{
    private Contractor.View mView;

    public OrderHistoryPresenter(Contractor.View View) {
        mView = View;
    }

    @Override
    public void addToHistory(Product product) {
        database d = new database();
        Order o = new Order();

        d.setListener(new Listener.OnComplete() {
            @Override
            public void onSuccessListener() {
                mView.onSuccess();
            }

            @Override
            public void onFailureListener() {
                mView.onError();
            }
        });

        o.setCategory(product.getCategory().toString());
        o.setName(product.getItemName());
        o.setProductId(product.getItemId());
        o.setStatus(Constants.Status.DELIVERED);
        o.setThumbnail(product.getItemImage());

        d.addOrderToHistory(o);

    }
}
