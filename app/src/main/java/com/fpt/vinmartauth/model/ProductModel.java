package com.fpt.vinmartauth.model;

import android.util.Log;

import com.fpt.vinmartauth.entity.Product;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ProductModel {
    private final FirebaseFirestore instance = FirestoreInstance.getInstance();
    private final String SUCCESS_TAG = "Success request: ";
    private final String ERROR_TAG = "Error request: ";

    public void getAllProduct(GetAllProductsCallbacks callbacks){
        CollectionReference productsCollectionRef = instance.collection("products");
        productsCollectionRef.get().addOnCompleteListener(task -> {
           if(task.isSuccessful()){
               QuerySnapshot snapshot = task.getResult();
               List<Product> products = new ArrayList<>();
                for (QueryDocumentSnapshot document: snapshot) {
                    Product product = document.toObject(Product.class);
                    products.add(product);
                }
                callbacks.onSuccess(products);
           }else {
               callbacks.onFailed();
           }
        });
    }

    public interface GetAllProductsCallbacks {
        void onSuccess(List<Product> products);
        void onFailed();
    }

    public Product getProductById(int id){
        CollectionReference productCollectionRef = instance.collection("products");
        Query productQuery = productCollectionRef.whereEqualTo("ID",id);
        productQuery.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Log.i(SUCCESS_TAG,"OK");
            } else {
                Log.i(ERROR_TAG,"Something wrong happened");
            }
        });
        return null;
    }
//    public Product getProductByName(String name){
//        CollectionReference productCollectionRef = instance.collection("products");
//        Query productQuery = productCollectionRef.whereEqualTo("ID",id);
//        productQuery.get().addOnCompleteListener(task -> {
//            if(task.isSuccessful()){
//                Log.i(SUCCESS_TAG,"OK");
//            } else {
//                Log.i(ERROR_TAG,"Something wrong happened");
//            }
//        });
//        return null;
//    }
}
