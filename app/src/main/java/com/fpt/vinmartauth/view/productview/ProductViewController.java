package com.fpt.vinmartauth.view.productview;

import com.fpt.vinmartauth.entity.Product;
import com.fpt.vinmartauth.model.ProductModel;

import java.util.List;

public class ProductViewController {
    private ProductView view;
    private final ProductModel productModel = new ProductModel();

    void setView(ProductView view) {
        this.view = view;
    }

    void fetchAllProducts() {
        productModel.getAllProduct(new ProductModel.GetAllProductsCallbacks() {
            @Override
            public void onSuccess(List<Product> products) {
                view.setProducts(products);
            }

            @Override
            public void onFailed() {

            }
        });
    }
}
