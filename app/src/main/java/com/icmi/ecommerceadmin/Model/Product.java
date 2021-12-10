package com.icmi.ecommerceadmin.Model;

import com.icmi.ecommerceadmin.Utils.Constants;

public class Product {
    private String itemImage;
    private String itemName;
    private String itemPrice;
    private String itemId;
    private Constants.Items category;


    public Constants.Items getCategory() {
        return category;
    }

    public void setCategory(Constants.Items category) {
        this.category = category;
    }

    public Product() {
    }

    public Product(String itemImage, String itemName, String itemPrice, Constants.Items category, String itemId) {
        this.itemImage = itemImage;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemId = itemId;
        this.category = category;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }


    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }
}
