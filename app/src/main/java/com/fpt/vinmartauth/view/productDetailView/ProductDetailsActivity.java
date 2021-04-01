package com.fpt.vinmartauth.view.productDetailView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fpt.vinmartauth.R;
import com.fpt.vinmartauth.entity.Cart;
import com.fpt.vinmartauth.entity.CartItem;
import com.fpt.vinmartauth.entity.Product;
import com.fpt.vinmartauth.view.LoginActivity;
import com.fpt.vinmartauth.view.fragment.cartView.CartView;
import com.fpt.vinmartauth.view.fragment.cartView.CartViewController;
import com.fpt.vinmartauth.view.fragment.cartView.UserSession;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity implements CartView {

    private CartViewController controller = new CartViewController();
    private final UserSession session = UserSession.getInstance();
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        product  = (Product) getIntent().getSerializableExtra("product");
        controller.setView(this);
        ImageView productImg = findViewById(R.id.ivProductDetail);
        TextView productDetailTitle = findViewById(R.id.tvProductDetailTitle);
        productDetailTitle.setText(product.getTitle());
        TextView productDetailDescription = findViewById(R.id.tvProductDetailDescription);
        productDetailDescription.setText(product.getDescription());
        TextView productDetailPrice = findViewById(R.id.tvProductDetailPrice);
        productDetailPrice.setText(product.getPrice() + " đ");
        Picasso.get().load(product.getImage()).into(productImg);
        Button btnAdd = findViewById(R.id.btnAddProduct);
        btnAdd.setOnClickListener(v -> {
            if(!session.getUID().isEmpty()) {
                if (session.getCartID() == null || "".equals(session.getCartID())) {
                    controller.fetchUserCart(session.getUID(), this);
                } else {
                    controller.doAddProductItem(product, session.getCartID(), this);
                }
            } else {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setCartItems(List<CartItem> cartItemList) {

    }

    @Override
    public void setMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setCart(Cart cart) {
        session.setCartID(cart.getDocumentID());
        controller.doAddProductItem(product ,cart.getDocumentID(), this);
    }
}