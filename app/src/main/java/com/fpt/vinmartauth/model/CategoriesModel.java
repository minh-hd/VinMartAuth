package com.fpt.vinmartauth.model;

import android.util.Log;

import com.fpt.vinmartauth.entity.Category;
import com.fpt.vinmartauth.entity.Product;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
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

    //comment no di
//    public Category getCategoryById(String id){
//
//        DocumentReference documentReference = instance.collection("categories").document(id);
//        documentReference.get().addOnCompleteListener(new OnSuccessListener<DocumentSnapshot>(){
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                Category category = documentSnapshot.toObject(Category.class);
//            }
//        });
//
//    }
}
