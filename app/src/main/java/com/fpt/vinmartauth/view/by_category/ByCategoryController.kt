package com.fpt.vinmartauth.view.by_category

import com.fpt.vinmartauth.entity.Category
import com.fpt.vinmartauth.entity.Product
import com.fpt.vinmartauth.model.ProductModel
import com.fpt.vinmartauth.model.ProductModel.GetProductsByTitleCallbacks
import java.util.*

class ByCategoryController {
    private var view: ByCategoryView? = null
    private val productModel = ProductModel()
    fun setView(view: ByCategoryView?) {
        this.view = view
    }

    fun fetchProductByCategory(category: Category?, sort: Int) {
        productModel.getProductByCategoryId(category, object : GetProductsByTitleCallbacks {
            override fun onSuccess(products: List<Product>) {
                when (sort) {
                    0 -> {
                        view!!.setProducts(products.sortedByDescending { it.id })
                    }
                    2 -> {
                        view!!.setProducts(products.sortedByDescending { it.price})
                    }
                    1 -> {
                        view!!.setProducts(products.sortedBy { it.price })
                    }
                    else -> view!!.setProducts(products)
                }
            }

            override fun onFailed() {
                view!!.setProducts(ArrayList())
            }
        })
    }
}