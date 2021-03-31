package com.fpt.vinmartauth.view.fragment;

import com.fpt.vinmartauth.entity.Cart;
import com.fpt.vinmartauth.entity.CartItem;
import com.fpt.vinmartauth.model.CartItemModel;

import java.util.List;

public class CartViewController {
    private CartView view;
    private final CartItemModel cartItemModel = new CartItemModel();

    void setView(CartView view) {
        this.view = view;
    }

    void fetchCartItems() {
        cartItemModel.getAllCartItem(new CartItemModel.GetAllCartsCallbacks() {

            @Override
            public void onSuccess(List<CartItem> items) {
                view.setCart(items);
            }

            @Override
            public void onFailure() { }
        });
    }

    void fetchCartItemsTotal() {
        cartItemModel.getTotalItemsPrice(new CartItemModel.GetTotalPricesCallbacks() {
            @Override
            public void onSuccess(int cartTotals) {
                view.setTotal(cartTotals);
            }
        });
    }

    void fetchCartAfterDelete(String itemID) {
        cartItemModel.getCartAfterDelete(itemID, new CartItemModel.GetAllCartsCallbacks() {
            @Override
            public void onSuccess(List<CartItem> items) {
                view.setCart(items);
            }

            @Override
            public void onFailure() {}
        });
    }

    void doCartItemsUpdate(List<CartItem> items) {
        cartItemModel.updateCartItemsQuantity(items, new CartItemModel.GetAllCartsCallbacks() {
            @Override
            public void onSuccess(List<CartItem> items) {
                view.setCart(items);
            }

            @Override
            public void onFailure() {}
        });
    }

    // invoke on main activity started
    void fetchUserAbandonedCart() {
        // Get Current user ID
        String UID = "";
        cartItemModel.getCurrentUserCart(UID, new CartItemModel.GetCurrentUserCartCallbacks() {
            @Override
            public void onSuccess(Cart cart) {
                // SET CURRENT CART HERE
            }
        });
    }

    void doCartCheckout(List<CartItem> items) {
        // Use Java 8 Stream
        int totalCart = items.stream().mapToInt(i -> Integer.parseInt(i.getProductPrice()) * Integer.parseInt(i.getQuantity())).sum();
        // Get Current cart
        String currentCartID = "";
        cartItemModel.updateCartForCheckout(currentCartID, totalCart, new CartItemModel.UpdateCartForCheckoutCallbacks() {
            @Override
            public void onSuccess(String successMessage) {
                // set checkout success message
                view.setMessage(successMessage);
            }

            @Override
            public void onFailure(String failureMessage) {
                // set checkout failure message
                view.setMessage(failureMessage);
            }
        });
    }

}
