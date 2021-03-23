package com.fpt.vinmartauth.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.fpt.vinmartauth.R;
import com.fpt.vinmartauth.entity.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends Adapter {
    private final ArrayList<Product> items = new ArrayList<>();

    public ProductAdapter(final ArrayList<Product> i) {
        if (i != null) {
            this.items.addAll(i);
        }
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_product, parent, false);
        return new ProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ProductAdapter.ViewHolder) holder).bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView itemName;
        private final TextView itemBrand;
        private final TextView itemPrice;
        private final ImageView itemImage;

        public ViewHolder(final View view) {
            super(view);
            itemName = view.findViewById(R.id.tvProductName);
            itemBrand = view.findViewById(R.id.tvProductBrand);
            itemPrice = view.findViewById(R.id.tvProductPrice);
            itemImage = view.findViewById(R.id.ivProductImage);
        }

        public void bind(final Product item) {
            itemName.setText(item.getProductName());
            itemBrand.setText(item.getProductBrand());
            itemPrice.setText(item.getProductPrice() + "₫");
            Picasso.get().load(item.getImageSrc()).into(itemImage);
        }
    }

}
