package com.fpt.vinmartauth.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.fpt.vinmartauth.entity.Category;
import com.fpt.vinmartauth.entity.Product;
import com.fpt.vinmartauth.utils.TextUtils;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductModel {
    private final FirebaseFirestore instance = FirestoreInstance.getInstance();
    private final String SUCCESS_TAG = "Success request: ";
    private final String ERROR_TAG = "Error request: ";

    public void getAllProduct(GetAllProductsCallbacks callbacks) {

        CollectionReference productsCollectionRef = instance.collection("products");
        productsCollectionRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot snapshot = task.getResult();
                List<Product> products = new ArrayList<>();
                for (QueryDocumentSnapshot document : snapshot) {
                    Product product = document.toObject(Product.class);
                    product.setId(document.getId());
                    products.add(product);
                    Log.d("anhntl", product.toString());
                }
                callbacks.onSuccess(products);
            } else {
                callbacks.onFailed();
            }
        });


    }


    public interface GetAllProductsCallbacks {
        void onSuccess(List<Product> products);

        void onFailed();
    }


    public void getProductByName(String title, GetProductsByTitleCallbacks callbacks) {
        //Reuse getAllProduct method
        getAllProduct(new GetAllProductsCallbacks() {
            @Override
            public void onSuccess(List<Product> products) {
                List<Product> filteredProducts = new ArrayList<>();
                products.forEach(product -> {
                    String formattedProductTitle = TextUtils.formatDiacriticalMarks(product.getTitle());
                    String formattedTitle = TextUtils.formatDiacriticalMarks(title);
                    if (formattedProductTitle.contains(formattedTitle)) {
                        filteredProducts.add(product);
                    }
                });
                callbacks.onSuccess(filteredProducts);

            }

            @Override
            public void onFailed() {
                callbacks.onFailed();
            }
        });
    }

    public interface GetProductsByTitleCallbacks {
        void onSuccess(List<Product> products);
        void onFailed();
    }

    public void getProductByCategoryId(Category category,GetProductsByTitleCallbacks callbacks) {
        getAllProduct(new GetAllProductsCallbacks() {
            @Override
            public void onSuccess(List<Product> products) {
                List<Product> filteredProducts = new ArrayList<>();
                products.forEach(product -> {
                    if (product.getCategory().getId().contains(category.getId())) {
                        filteredProducts.add(product);
                    }
                });
                callbacks.onSuccess(filteredProducts);

            }
            @Override
            public void onFailed() {
                callbacks.onFailed();
            }
        });
    }

    public void getAllProductSortByPrice(GetProductsByTitleCallbacks callbacks){
        getAllProduct(new GetAllProductsCallbacks() {
            @Override
            public void onSuccess(List<Product> products) {
//                List<Integer> filteredProducts = new ArrayList<>();
//                List<Product> p = new ArrayList<>();
                Collections.sort(products);
                callbacks.onSuccess(products);

//                Log.d("bbbbb",filteredProducts.toString());
            }

            @Override
            public void onFailed() {
                callbacks.onFailed();
            }
        });
    }
}
