package com.fpt.vinmartauth.view.fragment.cartView;

import com.fpt.vinmartauth.entity.Cart;
import com.fpt.vinmartauth.entity.CartItem;

import java.util.List;

public interface CartView {
    void setCartItems(List<CartItem> cartItemList);
    void setMessage(String message);
    void setCart(Cart cart);
}
