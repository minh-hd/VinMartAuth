package com.fpt.vinmartauth.view.productlist;

import com.fpt.vinmartauth.entity.Product;
import com.fpt.vinmartauth.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class ProductListController {
    private ProductListView view;
    private final ProductModel productModel = new ProductModel();

    void setView(ProductListView view) {
        this.view = view;
    }

    void fetchProductById(String title) {
        productModel.getProductByName(title, new ProductModel.GetProductsByTitleCallbacks() {

            @Override
            public void onSuccess(List<Product> products) {
                view.setData(products);
            }

            @Override
            public void onFailed() {
                view.setData(new ArrayList<>());
            }
        });
    }
}
