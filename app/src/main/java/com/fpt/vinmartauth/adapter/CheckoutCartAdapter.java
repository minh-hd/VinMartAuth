package com.fpt.vinmartauth.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fpt.vinmartauth.R;
import com.fpt.vinmartauth.entity.CartItem;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CheckoutCartAdapter extends RecyclerView.Adapter {
    private final ArrayList<CartItem> items = new ArrayList<>();
    private CartItemAdapterListener listener;
    public void setListener(CartItemAdapterListener listener) {
        this.listener = listener;
    }


    public void setData(List<CartItem> cartItems) {
        items.clear();
        items.addAll(cartItems);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CheckoutCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row_cart_item, parent, false);
        return new CheckoutCartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CartItem cartItem = items.get(position);
        ((CheckoutCartAdapter.ViewHolder) holder).bind(cartItem);

    }
    public interface CartItemAdapterListener {
        void onCartItemClick(CartItem cartItem);
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView itemName;
        private final TextView itemAmount;
        private final TextView itemPrice;
        private final ImageView itemImage;

        public ViewHolder(final View view) {
            super(view);
            itemName = view.findViewById(R.id.tv_product_title);
            itemAmount = view.findViewById(R.id.tv_amount);
            itemPrice = view.findViewById(R.id.tv_product_price);
            itemImage = view.findViewById(R.id.img_product);
        }

        public void bind(final CartItem item) {
            itemName.setText(item.getProductTitle());
            itemAmount.setText(item.getQuantity());
            itemPrice.setText(item.getProductPrice() + " ₫");
            if (!item.getProductImage().isEmpty()) {
                Picasso.get().load(item.getProductImage()).into(itemImage);
            }
        }
    }
//
}

