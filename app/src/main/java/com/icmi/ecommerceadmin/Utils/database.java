package com.icmi.ecommerceadmin.Utils;

import android.net.Uri;
import android.util.Log;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.storage.StorageReference;
import com.icmi.ecommerceadmin.Model.Order;
import com.icmi.ecommerceadmin.Model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class database {

    private Listener.OnComplete listener;

    public database() {

    }

    public void setListener(Listener.OnComplete listener) {
        this.listener = listener;
    }

    public database(Listener.OnComplete listener) {
        this.listener = listener;
    }

    public void addProduct(final Product product, List<String> images) {
        if (listener != null) {
            DocumentReference doc = Constants.getProductsReference(product.getCategory()).document();

            product.setItemId(doc.getId());

            StorageReference sr = Constants.getThumbnailSR(product);

            Log.d("DB", "addProduct: " + doc.getId());

            sr.putFile(Uri.parse(product.getItemImage())).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Log.d("DB", "i mage Uploaded");

                    sr.getDownloadUrl().addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()) {
                            Log.d("DB", "Image download uri: " + Objects.requireNonNull(task1.getResult()).toString());
                            product.setItemImage(task1.getResult().toString());
                            uploadProduct(product, images);

                        } else {
                            Log.d("DB", "task isn't complete");
                        }
                    });

                } else {
                    Log.d("DB", "Image Upload Failed");
                    listener.onFailureListener();
                }
            });

        } else {
            Log.d("DB", "getProduct: Listener not set");
        }

    }


    private void uploadProduct(Product product, List<String> images) {

        DocumentReference doc = Constants.getProductsReference(product.getCategory()).document();

        doc.set(product).addOnSuccessListener(aVoid -> {
            Log.d("DB", "addProduct: Successfull");
            if (listener != null) {

                for (int i = 0; i < images.size(); i++) {
                    addImage(Uri.parse(images.get(i)), product);
                }

                listener.onSuccessListener();
            }
        }).addOnFailureListener(e -> {
            Log.d("DB", "addProduct: Failed : " + e.getMessage());
            if (listener != null)
                listener.onFailureListener();
        });
    }


    public void addImage(Uri image, Product product) {

        StorageReference sr = Constants.getimageSR(product, image);

        sr.putFile(image).addOnCompleteListener(task -> {                   //Upload Image
            if (task.isSuccessful()) {
                Log.d("DB", "image Uploaded");

                sr.getDownloadUrl().addOnCompleteListener(task1 -> {            //get Image download link
                    if (task1.isSuccessful()) {

                        Log.d("DB", "Image download uri: " + task1.getResult().toString());

                        addImageInCollection(product, task1.getResult().toString());


                    } else {
                        Log.d("DB", "task isn't complete");
                    }
                });

            } else {
                Log.d("DB", "Image Upload Failed");
                listener.onFailureListener();
            }
        });


    }

    private void addImageInCollection(Product product, String downloadUri) {

        CollectionReference cr = Constants.getProductImagesReference(product);
        Map<String, String> img = new HashMap<>();
        img.put("Uri", downloadUri);

        cr.add(img).addOnCompleteListener(task2 -> {                //add image link to product's images collection
            if (task2.isSuccessful())
                Log.d("DB", "addInCollection: image added in collection");

        });

    }


    public void addOrderToHistory(Order order) {
        CollectionReference cr = Constants.getHistoryRef();

        cr.document()
                .set(order)
                .addOnSuccessListener(aVoid -> {
                    if (listener != null)
                        listener.onSuccessListener();
                })
                .addOnFailureListener(e -> {
                    if (listener != null)
                        listener.onFailureListener();
                });
    }


}
