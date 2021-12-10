package com.icmi.ecommerceadmin.Activity;

import com.icmi.ecommerceadmin.Model.Product;

import java.util.List;

public interface AddProductContract {
    interface view{
        void ShowLoading();
        void HideLoading();
        void OnSuccess();
        void OnFailure(String errorMessage);
    }

    interface Presenter{
        void RegisterProduct(Product newProduct, List<String> images);

    }
}
