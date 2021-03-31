package com.fpt.vinmartauth.model;

import android.util.Log;

import com.fpt.vinmartauth.entity.Cart;
import com.fpt.vinmartauth.entity.CartItem;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CartItemModel {
    private final FirebaseFirestore instance;
    private final String SUCCESS_TAG = "Success request: ";
    private final String ERROR_TAG = "Error request: ";
    private final String CART_COLLECTION_PATH = "cart";
    private final String CART_IS_CHECKOUT_FIELD = "isCheckout";
    private final String CART_UID_FIELD = "UID";
    private final String ITEM_COLLECTION_PATH = "items";
    private final String ITEM_DOCUMENT_ID_FIELD = "documentID";
    private final String ITEM_PRODUCT_TITLE_FIELD = "productTitle";
    private final String ITEM_PRODUCT_PRICE_FIELD = "productPrice";
    private final String ITEM_PRODUCT_IMAGE_FIELD = "productImage";
    private final String ITEM_PRODUCT_PRODUCT_ID_FIELD = "productID";
    private final String ITEM_QUANTITY_FIELD = "quantity";
    private final CollectionReference cartRef;
    private Cart cartSession;
    private List<CartItem> itemListSession;

    public CartItemModel() {
        instance = FirestoreInstance.getInstance();
        cartRef = instance.collection(CART_COLLECTION_PATH);
    }

    public void getCheckoutCartAndItems(GetCheckoutCartAndItemsCallbacks callbacks) {
        // Get cart
        CollectionReference ref = instance.collection("carts");
        ref.document("Cax001")
                .collection("items")
                .get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot collection = task.getResult();
                List<CartItem> items = new ArrayList<>();
                for (DocumentSnapshot doc : collection) {
                    CartItem item = doc.toObject(CartItem.class);
                    items.add(item);
                    Log.d("duchm", item.toString());
                }
                callbacks.onSuccess(items);
            } else {
                callbacks.onFailure();
            }
        });
    }

    public void getTotalPrices(GetTotalPricesCallbacks callbacks) {
        getCheckoutCartAndItems(new GetCheckoutCartAndItemsCallbacks() {
            @Override
            public void onSuccess(List<CartItem> items) {
                int itemTotal = items.stream().mapToInt(item -> Integer.parseInt(item.getProductPrice()) * Integer.parseInt(item.getQuantity())).sum();
                Log.d("anhdt", String.valueOf(itemTotal));
                callbacks.onSuccess(itemTotal);
            }
            @Override
            public void onFailure() {
            }
        });
    }
    public interface GetTotalPricesCallbacks {
        void onSuccess(int cartTotals);
    }

    public void getTotalAmount(GetTotalAmountCallbacks callbacks) {
        getCheckoutCartAndItems(new GetCheckoutCartAndItemsCallbacks() {
            @Override
            public void onSuccess(List<CartItem> items) {
                int itemTotalAmount = items.stream().mapToInt(item -> Integer.parseInt(item.getQuantity())).sum();
                Log.d("anhdt", String.valueOf(itemTotalAmount));
                callbacks.onSuccess(itemTotalAmount);
            }
            @Override
            public void onFailure() {
            }
        });
    }
    public interface GetTotalAmountCallbacks {
        void onSuccess(int cartAmounTotals);
    }
    public interface GetCheckoutCartAndItemsCallbacks {
        void onSuccess(List<CartItem> cartItemList);
        void onFailure();
    }
}
