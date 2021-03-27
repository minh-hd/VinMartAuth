package com.fpt.vinmartauth.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.fpt.vinmartauth.entity.Category;
import com.fpt.vinmartauth.entity.Product;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void getProductById(String id){
        DocumentReference documentReference = instance.collection("products").document(id);
        documentReference.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                DocumentSnapshot document = task.getResult();
                if(document.exists()) {
                    Log.i(SUCCESS_TAG, "OK");
                } else {
                    Log.i(ERROR_TAG,"Something wrong happened");
                }
            } else {
                Log.i(ERROR_TAG,"Get fail");
            }
        });
    }
    public void getProductByName(String title){
        DocumentReference documentReference = instance.collection("products").document(title);;
        documentReference.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                DocumentSnapshot document = task.getResult();
                if(document.exists()) {
                    Log.i(SUCCESS_TAG, "OK");
                } else {
                    Log.i(ERROR_TAG,"Something wrong happened");
                }
            } else {
                Log.i(ERROR_TAG,"Something wrong happened");
            }
        });
    }

    public void addNewProduct(Product product){
        Map<String, Object> data = new HashMap<>();
        data.put("category", product.getCategory());
        data.put("description", product.getDescription());
        data.put("image", product.getImage());
        data.put("price",product.getPrice());
        data.put("quantity",product.getQuantity());
        data.put("title",product.getTitle());
        data.put("vendor",product.getVendor());

        instance.collection("products")
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(SUCCESS_TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(ERROR_TAG, "Error adding document", e);
                    }
                });
    }

    public void deleteDocument(String id) {
        instance.collection("products").document(id)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(SUCCESS_TAG, "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(ERROR_TAG, "Error deleting document", e);
                    }
                });
    }

    public void updateDocument(String id, Product product) {
        // [START update_document]
        DocumentReference productRef = instance.collection("products").document(id);

        productRef
                .update("quantity", product.getQuantity())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(SUCCESS_TAG, "DocumentSnapshot successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(ERROR_TAG, "Error updating document", e);
                    }
                });
    }
}
