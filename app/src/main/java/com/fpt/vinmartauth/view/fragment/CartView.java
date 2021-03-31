package com.fpt.vinmartauth.view.fragment;

import com.fpt.vinmartauth.entity.CartItem;

import java.util.List;

public interface CartView {
    void setCart(List<CartItem> cartItemList);
    void setTotal(int cartTotal);
    void setMessage(String message);
}
