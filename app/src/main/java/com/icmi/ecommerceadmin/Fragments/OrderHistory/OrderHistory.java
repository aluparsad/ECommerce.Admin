package com.icmi.ecommerceadmin.Fragments.OrderHistory;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.icmi.ecommerceadmin.Model.Product;
import com.icmi.ecommerceadmin.R;
import com.icmi.ecommerceadmin.Utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class OrderHistory extends AppCompatActivity implements Contractor.View {

    List<Product> products;

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fiagment_history, container, false);
//    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        InitializeView(view);
//
//        Contractor.Presenter p = new OrderHistoryPresenter(this);
//
//        for(Product a : products){
//            p.addToHistory(a);
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fiagment_history);

        InitializeView();

        Contractor.Presenter p = new OrderHistoryPresenter(this);

        for(Product a : products){
            p.addToHistory(a);
        }

    }



    private void InitializeView() {

        products = new ArrayList<>();

        Product a = new Product(),
                b = new Product(),
                c = new Product(),
                d = new Product();

        a.setCategory(Constants.Items.valueOf("FASHION"));
        a.setItemId("1GAhgrBhlDzYO6vDzhEW");
        a.setItemImage("https://firebasestorage.googleapis.com/v0/b/ecommerce-3be91.appspot.com/o/FASHION%2F1GAhgrBhlDzYO6vDzhEW%2FThumbnail?alt=media&token=f6f40d90-1794-4165-b5cf-b57dba6ed50d");
        a.setItemName("cardio wale ldke");

        b.setItemName("cool buddy");
        b.setItemImage("https://firebasestorage.googleapis.com/v0/b/ecommerce-3be91.appspot.com/o/FASHION%2FoyqqrWoL1BJMscjtXm5W%2FThumbnail?alt=media&token=51d0f8c8-60d1-4b2d-b738-0da29dda4a00");
        b.setItemId("oyqqrWoL1BJMscjtXm5W");
        b.setCategory(Constants.Items.valueOf("FASHION"));

        c.setItemImage("https://firebasestorage.googleapis.com/v0/b/ecommerce-3be91.appspot.com/o/MOBILES%2FRIBJkhP0zYUBo8pDlj65%2FThumbnail?alt=media&token=77326ac2-863a-44f8-8400-46c77fbf8479");
        c.setItemName("coading");
        c.setItemId("RIBJkhP0zYUBo8pDlj65");
        c.setCategory(Constants.Items.valueOf("MOBILES"));

        d.setItemName("cool guy");
        d.setItemImage("https://firebasestorage.googleapis.com/v0/b/ecommerce-3be91.appspot.com/o/APPLIANCES%2F97lClDYPwobcGMf8fcRK%2FThumbnail?alt=media&token=97d5c28f-87fa-4048-a109-5b8dc97eca9c");
        d.setItemId("97lClDYPwobcGMf8fcRK");
        d.setCategory(Constants.Items.valueOf("APPLIANCES"));

        products.add(a);
        products.add(b);
        products.add(c);
        products.add(d);

    }

    @Override
    public void onSuccess() {
        Log.d("Admin", "onSuccess: Added");
    }

    @Override
    public void onError() {

    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {

    }
}
