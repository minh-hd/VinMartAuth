package com.fpt.vinmartauth.adapter;

import android.content.Intent;
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
import java.util.List;

public class ProductAdapter extends Adapter {
    private final ArrayList<Product> items = new ArrayList<>();
    private ProductAdapterListener listener;

    public void setListener(ProductAdapterListener listener) {
        this.listener = listener;
    }

    public void setData(List<Product> products) {
        items.clear();
        items.addAll(products);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_product, parent, false);
        return new ProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Product product = items.get(position);
        ((ProductAdapter.ViewHolder) holder).bind(product);
        holder.itemView.setOnClickListener(v -> listener.onProductClick(product)
        );
    }

    public interface ProductAdapterListener {
        void onProductClick(Product product);
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
            itemName.setText(item.getTitle());
            itemBrand.setText(item.getVendor());
            itemPrice.setText(item.getPrice() + "₫");
            if (!item.getImage().isEmpty()) {
                Picasso.get().load(item.getImage()).into(itemImage);
            }
        }
    }

}
