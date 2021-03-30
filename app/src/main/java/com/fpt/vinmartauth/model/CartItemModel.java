package com.fpt.vinmartauth.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.fpt.vinmartauth.entity.Cart;
import com.fpt.vinmartauth.entity.CartItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CartItemModel {
    private final FirebaseFirestore instance;
    private final String SUCCESS_TAG = "Success request: ";
    private final String ERROR_TAG = "Error request: ";
    private final String CART_COLLECTION_PATH = "cart";
    private final String CART_ISCHECKOUT_FIELD = "isCheckout";
    private final String CART_UID_FIELD = "UID";
    private final String QUANTITY_FIELD = "quantity";
    private final String ITEM_COLLECTION_PATH = "items";
    private CollectionReference cartRef;
    private Cart cartSession;
    private List<CartItem> itemListSession;

    public CartItemModel() {
        instance = FirestoreInstance.getInstance();
        cartRef = instance.collection(CART_COLLECTION_PATH);

    }

    // method to create Cart
    //  -> get UID
    //  -> if this UID has already have cart and isCheckout is false => discard create
    //  -> else:
    //  -> create new cart ID
    //  -> create dummy cart
    //  -> create items dummy cart collection
    //  -> add to firebase:
    //      => dummy cart
    //      => items collection in cart
    public void createCart(String UID) {
        cartRef.whereEqualTo(CART_UID_FIELD,UID)
                .whereEqualTo(CART_ISCHECKOUT_FIELD,true)
                .addSnapshotListener((value, error) -> {
            if (value.isEmpty()) {
                cartRef.get().addOnCompleteListener(task -> {
                    String myDocID = new StringBuilder("Cax").append(task.getResult().size() + 1));
                    Cart newCart = new Cart();
                    newCart.setDocumentID(myDocID);
                    newCart.setUID(UID);
                    cartRef.document(myDocID).set(newCart);
                });
            }
        });
    }
    // method to create add cart item
    //  -> get cart
    //
    //
    //
    //
    // method to update cart
    // method to update cart item
    // method to delete cart item
    // method to get cart
    // method to get cart item

//    public void addCartItem(String cartID, Object product, GetCartItemCallbacks callbacks) {
//        CollectionReference cartItemRef = instance.collection(COLLECTION_PATH);
//        cartItemRef.get().addOnCompleteListener(task -> {
//            if (task.isSuccessful()) {
//                String newDocID = "CI".concat(String.valueOf(task.getResult().size() + 1));
//                CartItem newItem = new CartItem();
//                // ========== Set new Item attribute here ==========
//
//
//
//
//                // ==================================================
//                cartItemRef.document(newDocID).set(newItem).addOnCompleteListener(task1 -> {
//                    if (task1.isSuccessful()) {
//                        cartItemRef.get().addOnCompleteListener(task2 -> {
//                            callbacks.onSuccess(populateCartItem(task2));
//                        });
//                    } else {
//                        callbacks.onFailure();
//                    }
//                });
//            }
//        });
//    }
//
//
//    private List<CartItem> populateCartItem(Task<QuerySnapshot> task) {
//        List<CartItem> cartItemList = new ArrayList<>();
//        QuerySnapshot snapshots = task.getResult();
//        for (DocumentSnapshot snapshot : snapshots) {
//            CartItem item = snapshot.toObject(CartItem.class);
//            item.setDocumentID(snapshot.getId());
//            cartItemList.add(item);
//        }
//        return cartItemList;
//    }
//
//    public void getCartItem(String cartID, GetCartItemCallbacks callbacks) {
//        CollectionReference cartItemRef = instance.collection("cartItem");
//        Query cartItemQuery = cartItemRef.whereEqualTo("cartID", cartID);
//        // query documents
//        cartItemQuery.get().addOnCompleteListener(task -> {
//           if (task.isSuccessful()) {
//               callbacks.onSuccess(populateCartItem(task));
//           } else {
//               callbacks.onFailure();
//           }
//        });
//    }
//
//
//    public void deleteCartItem(String itemID, GetCartItemCallbacks callbacks) {
//        WriteBatch deleteBatch = instance.batch();
//        DocumentReference itemRef = instance.collection(COLLECTION_PATH).document(itemID);
//        deleteBatch.delete(itemRef);
//        deleteBatch.commit().addOnCompleteListener(task -> {
//            if (task.isSuccessful()) {
//
//            } else  {
//                callbacks.onFailure();
//            }
//        });
//    }
//
//
//    public void updateCartItem(List<CartItem> cartItemList, GetCartItemCallbacks callbacks) {
//        WriteBatch updateBatch = instance.batch();
//        for (CartItem item : cartItemList) {
//            final DocumentReference itemRef = instance.collection(COLLECTION_PATH).document(item.getDocumentID());
//            updateBatch.update(itemRef, QUANTITY_FIELD, item.getQuantity());
//        }
//            // commit batch
//            updateBatch.commit().addOnCompleteListener(task -> {
//                if (task.isSuccessful()) {
//
//                } else  {
//                    callbacks.onFailure();
//                }
//            });
//    }

    public interface GetCartItemCallbacks {
        void onSuccess(List<CartItem> cartItemList);
        void onFailure();
    }
}
