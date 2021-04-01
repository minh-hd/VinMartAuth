package com.fpt.vinmartauth.view.fragment.cartView;

import com.fpt.vinmartauth.entity.Cart;
import com.fpt.vinmartauth.entity.CartItem;
import com.fpt.vinmartauth.model.CartItemModel;

import java.util.List;

public class CartViewController {
    private CartView view;
    private final CartItemModel cartItemModel = new CartItemModel();
    private final UserSession session = UserSession.getInstance();

    void setView(CartView view) {
        this.view = view;
    }

    void fetchCartItems() {
        cartItemModel.getAllCartItem(session.getCartID(), new CartItemModel.GetAllCartsCallbacks() {

            @Override
            public void onSuccess(List<CartItem> items) {
                view.setCart(items);
            }

            @Override
            public void onFailure() { }
        });
    }

    void fetchCartItemsTotal() {
        cartItemModel.getTotalItemsPrice(session.getCartID(), new CartItemModel.GetTotalPricesCallbacks() {
            @Override
            public void onSuccess(int cartTotals) {
                view.setTotal(cartTotals);
            }
        });
    }

    void fetchCartAfterDelete(String itemID) {
        cartItemModel.getCartAfterDelete(session.getCartID(), itemID, new CartItemModel.GetAllCartsCallbacks() {
            @Override
            public void onSuccess(List<CartItem> items) {
                view.setCart(items);
            }

            @Override
            public void onFailure() {}
        });
    }

    void doCartItemsUpdate(List<CartItem> items) {
        cartItemModel.updateCartItemsQuantity(session.getCartID(), items, new CartItemModel.GetAllCartsCallbacks() {
            @Override
            public void onSuccess(List<CartItem> items) {
                view.setCart(items);
            }

            @Override
            public void onFailure() {}
        });
    }

    // invoke on main activity started
    void fetchUserCart() {
        cartItemModel.getCurrentUserCart(session.getUID(),new CartItemModel.GetCurrentUserCartCallbacks() {
            @Override
            public void onSuccess(Cart cart) {
                session.setCartID(cart.getDocumentID());
            }
        });
    }

    void doCartCheckout(List<CartItem> items) {
        // Use Java 8 Stream
        int totalCart = items.stream().mapToInt(i -> Integer.parseInt(i.getProductPrice()) * Integer.parseInt(i.getQuantity())).sum();
        // Get Current cart
        cartItemModel.updateCartForCheckout(session.getCartID(), totalCart, new CartItemModel.UpdateCartForCheckoutCallbacks() {
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
