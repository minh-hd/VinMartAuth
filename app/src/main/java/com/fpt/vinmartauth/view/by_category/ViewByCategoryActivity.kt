package com.fpt.vinmartauth.view.by_category

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

class ViewByCategoryActivity : AppCompatActivity(), ByCategoryView {
    var controller = ByCategoryController()
    private val productAdapter = ProductAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_by_category)
        findViewById<TextView>(R.id.tvCategoryTitle).text = intent.getStringExtra("CategoryName")
        val catID = intent.getStringExtra("CategoryID")
        val view = findViewById<RecyclerView>(R.id.rvViewByCategory)
        view.adapter = productAdapter
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
}
