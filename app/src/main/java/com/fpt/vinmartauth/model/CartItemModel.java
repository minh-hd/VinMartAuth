package com.fpt.vinmartauth.model;

import android.annotation.SuppressLint;
import android.util.Log;

import com.fpt.vinmartauth.entity.Cart;
import com.fpt.vinmartauth.entity.CartItem;
import com.fpt.vinmartauth.entity.Product;
import com.fpt.vinmartauth.view.fragment.cartView.UserSession;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CartItemModel {
    private final FirebaseFirestore instance;
    private final String SUCCESS_TAG = "SUCCESS";
    private final String ERROR_TAG = "ERROR";
    private final String CART_COLLECTION_PATH = "carts";
    private final String CART_IS_CHECKOUT_FIELD = "isCheckout";
    private final String CART_UID_FIELD = "UID";
    private final String CART_UPDATED_AT_FIELD = "updatedAt";
    private final String CART_CART_TOTAL_FIELD = "cartTotal";
    private final String CART_DOCUMENT_ID_FIELD = "documentID";
    private final String ITEM_COLLECTION_PATH = "items";
    private final String ITEM_QUANTITY_FIELD = "quantity";
    private final String UPDATE_SUCCESS_MESSAGE = "Đơn hàng đặt thành công";
    private final String UPDATE_ERROR_MESSAGE = "Lỗi đặt hàng.";
    private final String CART_DOCUMENT_ID_PREFIX = "Cax";
    private final String ITEM_DOCUMENT_ID_PREFIX = "CI";
    private final String ITEM_DOCUMENT_START_ID = "CI01";


    public CartItemModel() {
        instance = FirestoreInstance.getInstance();
    }

    // method to get Cart by ID
    public void getCartByID(String UID, GetCartByIDCallbacks callbacks) {
        CollectionReference cartRef = instance.collection(CART_COLLECTION_PATH);
        Query query = cartRef.whereEqualTo(CART_UID_FIELD, UID);
        query.addSnapshotListener((value, error) -> {
            if (!value.isEmpty()) {
                Cart cart = null;
                for (DocumentSnapshot doc : value.getDocuments()) {
                    if (!doc.getBoolean(CART_IS_CHECKOUT_FIELD)) {
                        cart = doc.toObject(Cart.class);
                        cart.setCheckout(doc.getBoolean(CART_IS_CHECKOUT_FIELD));
                    }
                }
                callbacks.onSuccess(cart);
            } else {
                Log.d(ERROR_TAG, "Unable to get cart");
            }
        });
    }

    public interface GetCartByIDCallbacks {
        void onSuccess(Object cart);
    }

    // method to populate querySnapshot to List of Cart
    private List<Cart> populateCart(QuerySnapshot collection) {
        List<Cart> carts = new ArrayList<>();
        for (DocumentSnapshot doc : collection) {
            Cart c = doc.toObject(Cart.class);
            c.setCheckout(doc.getBoolean(CART_IS_CHECKOUT_FIELD));
            carts.add(c);
        }
        return carts;
    }

    // method to create add cart item
    @SuppressLint("DefaultLocale")
    public void getCurrentUserCart(String UID, GetCurrentUserCartCallbacks callbacks) {
        CollectionReference cartRef = instance.collection(CART_COLLECTION_PATH);
        cartRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot collection = task.getResult();
                List<Cart> allCarts = populateCart(collection);
                // Using Java 8 Stream(), these 2 lines find cart with right UID and isCheckout = false in list, else return null
                Cart cart = allCarts.stream()
                        .filter(c -> UID.equals(c.getUID()) && !c.isCheckout()).findFirst().orElse(new Cart());
                // if cart is null, create new cart
                if (cart.getDocumentID() == null || "".equals(cart.getDocumentID())) {
                    cart.setUID(UID);
                    // Generate new documentID
                    cart.setDocumentID(CART_DOCUMENT_ID_PREFIX.concat(String.format("%03d" , allCarts.size() + 1)));
                    cart.setCartTotal("");
                    cartRef.document(cart.getDocumentID()).set(cart).addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()) {
                            Log.d(SUCCESS_TAG, "Create new cart for user");
                            callbacks.onSuccess(cart);
                        } else {
                            Log.d(ERROR_TAG, "Unable to create new cart");
                        }
                    });
                }
            } else {
                Log.d(ERROR_TAG, "Unable to get all cart");
            }
        });
    }

    public interface GetCurrentUserCartCallbacks {
        void onSuccess(Cart cart);
    }

    private List<CartItem> populateCartItem(QuerySnapshot collection) {
        List<CartItem> items = new ArrayList<>();
        for (DocumentSnapshot doc : collection) {
            CartItem item = doc.toObject(CartItem.class);
            items.add(item);
        }
        return items;
    }

    // method to add cart items
    @SuppressLint("DefaultLocale")
    public void addCartItems(Product product, String cartID) {
        // getAllItemInCart()
        DocumentReference docCart = instance.collection(CART_COLLECTION_PATH)
                .document(cartID);
        docCart.get().addOnCompleteListener(task -> {
            // if cart exists, do:
            if (task.isSuccessful() && cartID.equals(task.getResult().get(CART_DOCUMENT_ID_FIELD))) {
                docCart.collection(ITEM_COLLECTION_PATH).get()
                        .addOnCompleteListener(task1 -> {
                            // if sub-collection "items" exists, do:
                        if (task.isSuccessful() && task1.getResult().size() > 0) {
                            // populate DocumentSnapshot into List of CartItem for processing
                            QuerySnapshot collection = task1.getResult();
                            List<CartItem> items = populateCartItem(collection);
                            // check if product has exists in cart, return new CartItem if not found
                            CartItem item = items.stream()
                                    .filter(i -> product.getId().equals(i.getProductID())).findFirst().orElse(new CartItem());
                            if (item.getDocumentID() == null || "".equals(item.getDocumentID())) {
                                docCart.collection(ITEM_COLLECTION_PATH).document(item.getDocumentID())
                                        // product exists, set quantity of item by 1
                                        .update(ITEM_QUANTITY_FIELD, String.valueOf(Integer.parseInt(item.getQuantity()) + 1))
                                        .addOnCompleteListener(task2 -> {
                                            /* Do callbacks to send update quantity success message */
                                        });
                            } else {
                                // product not exists, add new item to sub-collection "items"
                                // generate item document ID, format type "CIXX" -> XX is number
                                item.setDocumentID(ITEM_DOCUMENT_ID_PREFIX.concat(String.format("%02d" , items.size() + 1)));
                                // set starting quantity as 1
                                item.setQuantity("1");
                                item.setProductID(product.getId());
                                item.setProductImage(product.getImage());
                                item.setProductTitle(product.getTitle());
                                item.setProductPrice(String.valueOf(product.getPrice()));
                                docCart.collection(ITEM_COLLECTION_PATH)
                                        .document(item.getDocumentID())
                                        .set(item).addOnSuccessListener(aVoid -> {
                                            /* Do callbacks to send item add success message */
                                        });
                            }
                        } else {
                            // add the first item to sub-collection "items"
                            CartItem item = new CartItem(ITEM_DOCUMENT_START_ID, product.getId(), product.getImage(), product.getTitle(),
                                    String.valueOf(product.getPrice()),"1");
                            docCart.collection(ITEM_COLLECTION_PATH)
                                    .document(item.getDocumentID())
                                    .set(item).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    /* Do callbacks to send item add success message */
                                }
                            });
                        }
                });
            }
        });
    }

    // method to get all cart items
    public void getAllCartItem(String cartID, GetAllCartsCallbacks callbacks){
        CollectionReference cartRef = instance.collection(CART_COLLECTION_PATH);
        cartRef.document(cartID)
                .collection(ITEM_COLLECTION_PATH)
                .get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot collection = task.getResult();
                List<CartItem> items = new ArrayList<>();
                for (DocumentSnapshot doc : collection) {
                    CartItem item = doc.toObject(CartItem.class);
                    items.add(item);
                }
                callbacks.onSuccess(items);
            } else {
                callbacks.onFailure();
            }
        });
    }

    public interface GetAllCartsCallbacks{
        void onSuccess(List<CartItem> items);
        void onFailure();
    }

    public void getTotalItemsPrice(String cartID, GetTotalPricesCallbacks callbacks) {
        getAllCartItem(cartID, new GetAllCartsCallbacks() {
            @Override
            public void onSuccess(List<CartItem> items) {
                int itemTotal = 0;
                for (CartItem item : items)
                    itemTotal += Integer.parseInt(item.getQuantity()) * Integer.parseInt(item.getProductPrice());
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

    // method to delete cart item
    public void getCartAfterDelete(String cartID, String itemID, GetAllCartsCallbacks callbacks) {
        CollectionReference cartRef = instance.collection(CART_COLLECTION_PATH);
        cartRef.document(cartID)
                .collection(ITEM_COLLECTION_PATH)
                .document(itemID).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                getAllCartItem(cartID, new GetAllCartsCallbacks() {
                    @Override
                    public void onSuccess(List<CartItem> items) {
                        callbacks.onSuccess(items);
                    }

                    @Override
                    public void onFailure() {}
                });
            }
        });
    }

    // method to update cart item quantity
    public void updateCartItemsQuantity(String cartID, List<CartItem> items, GetAllCartsCallbacks callbacks) {
        WriteBatch updateBatch =  instance.batch();
        CollectionReference cartItemRef = instance.collection(CART_COLLECTION_PATH)
                .document(cartID)
                .collection(ITEM_COLLECTION_PATH);
        // add item needed update to batch
        for (CartItem item : items) {
            DocumentReference docItem = cartItemRef.document(item.getDocumentID());
            updateBatch.update(docItem, ITEM_QUANTITY_FIELD,item.getQuantity());
        }
        updateBatch.commit().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.d(SUCCESS_TAG, UPDATE_SUCCESS_MESSAGE);
                getAllCartItem(cartID, new GetAllCartsCallbacks() {
                    @Override
                    public void onSuccess(List<CartItem> items) {callbacks.onSuccess(items);}

                    @Override
                    public void onFailure() {}
                });
            } else {
                Log.d(ERROR_TAG, UPDATE_ERROR_MESSAGE);
            }
        });
    }

    public void updateCartForCheckout(String cartID, int cartTotal, UpdateCartForCheckoutCallbacks callbacks) {
        WriteBatch updateBatch = instance.batch();
        DocumentReference docCart = instance.collection(CART_COLLECTION_PATH).document(cartID);
        updateBatch.update(docCart, CART_CART_TOTAL_FIELD, String.valueOf(cartTotal));
        updateBatch.update(docCart, CART_IS_CHECKOUT_FIELD, true);
        updateBatch.update(docCart, CART_UPDATED_AT_FIELD, new Timestamp(new Date()));
        updateBatch.commit().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                callbacks.onSuccess(UPDATE_SUCCESS_MESSAGE);
            } else {
                callbacks.onFailure(UPDATE_ERROR_MESSAGE);
            }
        });
    }

    public interface UpdateCartForCheckoutCallbacks {
        void onSuccess(String successMessage);
        void onFailure(String failureMessage);
    }

}
