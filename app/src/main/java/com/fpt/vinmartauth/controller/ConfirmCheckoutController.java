package com.fpt.vinmartauth.controller;

import android.util.Log;

import com.fpt.vinmartauth.entity.CartItem;
import com.fpt.vinmartauth.model.CartItemModel;

import java.util.List;

public class ConfirmCheckoutController {
    String UID;
    private CartItemCheckoutView view;
    private final CartItemModel cartItemModel = new CartItemModel();

    public void setView(CartItemCheckoutView view) {
        this.view = view;
    }

    public void fetchAllCartItem() {
        cartItemModel.getCheckoutCartAndItems(new CartItemModel.GetCheckoutCartAndItemsCallbacks() {
            @Override
            public void onSuccess(List<CartItem> cartItemList) {
                view.setProductsCO(cartItemList);
                Log.d("checkout", cartItemList.get(0).getProductTitle());
            }

            @Override
            public void onFailure() {
                Log.d("kienct", "pffffff");
            }
        });

    }
public void fetchCartTotal()
{
    cartItemModel.getTotalPrices(new CartItemModel.GetTotalPricesCallbacks() {
        @Override
        public void onSuccess(int cartTotals) {
            view.setTotal(cartTotals);
        }
    });
}

public void fetchAmountTotal()
{
    cartItemModel.getTotalAmount(new CartItemModel.GetTotalAmountCallbacks() {
        @Override
        public void onSuccess(int cartAmounTotals) {
            view.setAmount(cartAmounTotals);
        }
    });
}
    public interface CartItemCheckoutView {
        void setProductsCO(List<CartItem> cartItems);
        void setTotal(int total);
        void setAmount (int amount);
    }
}

