package com.fpt.vinmartauth.view.by_category

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fpt.vinmartauth.R
import com.fpt.vinmartauth.adapter.ProductAdapter
import com.fpt.vinmartauth.entity.Category
import com.fpt.vinmartauth.entity.Product
import com.fpt.vinmartauth.view.productDetailView.ProductDetailsActivity

class ViewByCategoryActivity : AppCompatActivity(), ByCategoryView, ProductAdapter.ProductAdapterListener {
    var controller = ByCategoryController()
    private val productAdapter = ProductAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_by_category)
        findViewById<TextView>(R.id.tvCategoryTitle).text = intent.getStringExtra("CategoryName")
        val catID = intent.getStringExtra("CategoryID")
        val view = findViewById<RecyclerView>(R.id.rvViewByCategory)
        view.adapter = productAdapter
        productAdapter.setListener(this)
        controller.setView(this)
        controller.fetchProductByCategory(Category(catID, "pff"))
        view.layoutManager = GridLayoutManager(view?.context, 2)
        findViewById<ImageView>(R.id.imageView5).setOnClickListener {
            finish()
        }
    }

    override fun setProducts(products: MutableList<Product>?) {
        productAdapter.setData(products)
    }

    override fun onProductClick(product: Product?) {
        val intent = Intent(this, ProductDetailsActivity::class.java)
        intent.putExtra("product", product)
        startActivity(intent)
    }
}
