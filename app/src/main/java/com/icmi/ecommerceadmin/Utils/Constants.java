package com.icmi.ecommerceadmin.Utils;

import android.net.Uri;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.icmi.ecommerceadmin.Model.Items;
import com.icmi.ecommerceadmin.Model.Product;


public class Constants {

    public enum Items {
        MOBILES, ELECTRONICS, HOME, PANTRY, FASHION, APPLIANCES
    }

    public enum Status {
        PROCESSING, SHIPPING, DELIVERED, RETURNED
    }


    public static String
            images = "IMAGES",
            users = "USERS",
            cart = "CART",
            liked = "LIKED",
            history = "HISTORY";


    public static FirebaseFirestore getDatabaseReference() {
        return FirebaseFirestore.getInstance();
    }

    public static CollectionReference getProductsReference(Items category) {
        return getDatabaseReference()
                .collection(String.valueOf(category));
    }


    public static StorageReference getimageSR(Product product, Uri image) {

        return FirebaseStorage.getInstance()
                .getReference(product.getCategory().toString() + '/'
                        + product.getItemId() + '/'
                        + images + "/"
                        + "Thumbnail");

    }

    public static StorageReference getThumbnailSR(Product product) {

        return FirebaseStorage.getInstance()
                .getReference(product.getCategory().toString() + '/'
                        + product.getItemId() + '/'
                        + "Thumbnail");

    }

    public static CollectionReference getProductImagesReference(Product product) {

        return getDatabaseReference()
                .collection(product.getCategory().toString())
                .document(product.getItemId())
                .collection(images);

    }


    public static DocumentReference getCurrentUserRef() {
        return getUsersReference()
                .document(String.valueOf(FirebaseAuth.getInstance().getUid()));
    }

    public static CollectionReference getUsersReference() {
        return getDatabaseReference()
                .collection(users);
    }

    public static CollectionReference getHistoryRef() {
        return getCurrentUserRef()
                .collection(history);
    }

    public static CollectionReference getCartReference() {
        return getCurrentUserRef()
                .collection(cart);
    }

    public static CollectionReference getLikedReference() {
        return getCurrentUserRef()
                .collection(liked);
    }

}
