package com.fpt.vinmartauth.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.fpt.vinmartauth.R;
import com.fpt.vinmartauth.entity.CartItem;
import com.google.android.gms.common.internal.service.Common;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder> {

    private final String PRODUCT_MAP_ID_FIELD = "ID";
    private final String PRODUCT_MAP_IMAGE_FIELD = "image";
    private final String PRODUCT_MAP_TITLE_FIELD = "title";
    private final String PRODUCT_MAP_PRICE_FIELD = "price";
    final private List<CartItem> cartItemList;
    private TextView tvCartTotal;

    public CartAdapter(List<CartItem> cartItemList, TextView tvCartTotal) {
        this.cartItemList = cartItemList;
        this.tvCartTotal = tvCartTotal;
    }

    // pass layout to ViewHolder
    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row_cart_item, parent, false);
        return new CartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartHolder holder, int position) {
            CartItem item = cartItemList.get(position);

            holder.tvProductTitle.setText(item.getProductTitle());

            holder.tvProductPrice.setText(
                    // Sum of total product
                    (new StringBuilder("Tổng:\t").append(Integer.parseInt(item.getProductPrice())
                            * Integer.parseInt(item.getQuantity())).append(" VND").toString())
            );
            // get image uri and load image into ImageView by Picasso library
            Picasso.get().load(cartItemList.get(position).getProductImage()).into(holder.productImage);

            holder.quantityUpDownNumBtn.setNumber(item.getQuantity());
            // auto save item quantity whenever change
            holder.quantityUpDownNumBtn.setOnValueChangeListener((view, oldValue, newValue) -> {
                item.setQuantity(String.valueOf(newValue));
                holder.tvProductPrice.setText(
                        (new StringBuilder("Tổng:\t").append(Integer.parseInt(item.getProductPrice())
                                * Integer.parseInt(item.getQuantity())).append(" VND").toString())
                );
                // re-calculate and set total price of all items in cart
                int cartTotal = 0;
                for (CartItem item1 : cartItemList) {
                    cartTotal += Integer.parseInt(item1.getProductPrice())
                            * Integer.parseInt(item1.getQuantity());
                }
                tvCartTotal.setText(new StringBuilder("").append(cartTotal).append("\tVND"));
            });
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public static class CartHolder extends RecyclerView.ViewHolder{

        public TextView tvProductTitle;
        public TextView tvProductPrice;
        public ImageView productImage;
        public ElegantNumberButton quantityUpDownNumBtn;

        public CartHolder(@NonNull View itemView) {
            super(itemView);
            tvProductTitle = itemView.findViewById(R.id.tv_product_title);
            tvProductPrice = itemView.findViewById(R.id.tv_product_price);
            productImage = itemView.findViewById(R.id.img_product);
            quantityUpDownNumBtn = itemView.findViewById(R.id.emb_quantity);
        }

    }
}

