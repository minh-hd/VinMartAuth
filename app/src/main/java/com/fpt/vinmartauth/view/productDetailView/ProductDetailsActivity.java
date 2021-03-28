package com.fpt.vinmartauth.view.productDetailView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fpt.vinmartauth.R;
import com.fpt.vinmartauth.entity.Product;
import com.squareup.picasso.Picasso;

public class ProductDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Product product  = (Product) getIntent().getSerializableExtra("product");

        ImageView productImg = findViewById(R.id.ivProductDetail);
        TextView productDetailTitle = findViewById(R.id.tvProductDetailTitle);
        productDetailTitle.setText(product.getTitle());
        TextView productDetailDescription = findViewById(R.id.tvProductDetailDescription);
        productDetailDescription.setText(product.getDescription());
        TextView productDetailPrice = findViewById(R.id.tvProductDetailPrice);
        productDetailPrice.setText(product.getPrice() + " đ");
        Picasso.get().load(product.getImage()).into(productImg);
    }
}