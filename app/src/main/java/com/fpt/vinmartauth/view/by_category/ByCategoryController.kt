package com.fpt.vinmartauth.view.by_category;

import com.fpt.vinmartauth.entity.Category;
import com.fpt.vinmartauth.entity.Product;
import com.fpt.vinmartauth.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class ByCategoryController {
    private ByCategoryView view;
    private final ProductModel productModel = new ProductModel();

    void setView(ByCategoryView view) {
        this.view = view;
    }

    void fetchProductByCategory(Category category) {
        productModel.getProductByCategoryId(category, new ProductModel.GetProductsByTitleCallbacks() {
            @Override
            public void onSuccess(List<Product> products) {
                view.setProducts(products);
            }

            @Override
            public void onFailed() {
                view.setProducts(new ArrayList<>());
            }

        });
    }
}
