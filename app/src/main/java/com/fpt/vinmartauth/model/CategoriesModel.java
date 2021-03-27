package com.fpt.vinmartauth.model;

import com.fpt.vinmartauth.entity.Category;
import com.fpt.vinmartauth.entity.Product;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CategoriesModel {
    private final FirebaseFirestore instance = FirestoreInstance.getInstance();
    private final String SUCCESS_TAG = "Success request: ";
    private final String ERROR_TAG = "Error request: ";

    public void getAllCategories(CategoriesModel.GetAllCategoriesCallbacks callbacks){
        CollectionReference categoriesCollectionRef = instance.collection("categories");
        categoriesCollectionRef.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                QuerySnapshot snapshot = task.getResult();
                List<Category> categories = new ArrayList<>();
                for (QueryDocumentSnapshot document: snapshot) {
                    Category category = document.toObject(Category.class);
                    categories.add(category);
                }
                callbacks.onSuccess(categories);
            }else {
                callbacks.onFailed();
            }
        });
    }


    public interface GetAllCategoriesCallbacks {
        void onSuccess(List<Category> categories);
        void onFailed();
    }
}
