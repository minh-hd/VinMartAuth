package com.fpt.vinmartauth.adapter;

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
import com.fpt.vinmartauth.view.fragment.cartView.CartFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder>{

    private final static List<CartItem> cartItemList = new ArrayList<>();
    private static final CartAdapter cartAdapter = new CartAdapter();

    private CartAdapter() {}

    public static CartAdapter getInstance() {
        if (cartAdapter != null) {
            return cartAdapter;
        } else return new CartAdapter();
    }

    public void setData(List<CartItem> cartItems) {
        cartItemList.clear();
        cartItemList.addAll(cartItems);
        notifyDataSetChanged();
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
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
            holder.bind(item);
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
        private final String TEXT_VIEW_PRICE_LABEL_HEAD = "Giá:\t";
        private final String CURRENCY_SYMBOL = " đ";

        public CartHolder(@NonNull View itemView) {
            super(itemView);
            tvProductTitle = itemView.findViewById(R.id.tv_product_title);
            tvProductPrice = itemView.findViewById(R.id.tv_product_price);
            productImage = itemView.findViewById(R.id.img_product);
            quantityUpDownNumBtn = itemView.findViewById(R.id.emb_quantity);
        }

        private String multipleQuantityWithPrice(String quantity, String price) {
            return TEXT_VIEW_PRICE_LABEL_HEAD
                    .concat(String.valueOf(Integer.parseInt(price)*Integer.parseInt(quantity)))
                            .concat(CURRENCY_SYMBOL);
        }

        public void bind(CartItem item) {
            tvProductTitle.setText(item.getProductTitle());
            tvProductPrice.setText(multipleQuantityWithPrice(item.getQuantity(), item.getProductPrice()));
            if (!item.getProductImage().isEmpty()) {
                Picasso.get().load(item.getProductImage()).into(productImage);
            }
            quantityUpDownNumBtn.setNumber(item.getQuantity());
            quantityUpDownNumBtn.setOnValueChangeListener((view, oldValue, newValue) -> {
                item.setQuantity(String.valueOf(newValue));
                cartItemList.get(cartItemList.indexOf(item)).setQuantity(String.valueOf(newValue));
                tvProductPrice.setText(multipleQuantityWithPrice(item.getQuantity(), item.getProductPrice()));
                // re-calculate and set total price of all items in cart
                int cartTotal = cartItemList.stream().
                        mapToInt(i -> Integer.parseInt(i.getProductPrice()) * Integer.parseInt(i.getQuantity())).sum();
                CartFragment.tvCartTotal.setText(String.valueOf(cartTotal).concat(CURRENCY_SYMBOL));
            });
        }
    }
}

