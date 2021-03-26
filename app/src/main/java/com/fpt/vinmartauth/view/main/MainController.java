package com.fpt.vinmartauth.view.main;

import com.fpt.vinmartauth.entity.Product;
import com.fpt.vinmartauth.model.ProductModel;

import java.util.List;

public class MainController {
    private MainView view;
    private final ProductModel productModel = new ProductModel();

    void setView(MainView view) {
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
