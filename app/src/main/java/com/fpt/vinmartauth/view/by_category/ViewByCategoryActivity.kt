package com.fpt.vinmartauth.view.by_category

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
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
        val tvNewest: TextView = findViewById(R.id.tvNewest)
        val tvSortBy: TextView = findViewById(R.id.tvSortBy)
        val tvAsc: TextView = findViewById(R.id.tvAscending)
        val tvDesc: TextView = findViewById(R.id.tvDescending)
        view.adapter = productAdapter
        productAdapter.setListener(this)
        controller.setView(this)
        controller.fetchProductByCategory(Category(catID, "pff"), 4)
        tvNewest.setOnClickListener {
            d("sorter", "newest")
            tvNewest.isSelected = true
            tvSortBy.isSelected = false
            tvAsc.isSelected = false
            tvDesc.isSelected = false
            controller.fetchProductByCategory(Category(catID, "pff"), 0)

        }
        tvDesc.setOnClickListener {
            d("sorter", "desc")
            tvNewest.isSelected = false
            tvSortBy.isSelected = true
            tvAsc.isSelected = false
            tvDesc.isSelected = true
            controller.fetchProductByCategory(Category(catID, "pff"), 2)
        }
        tvAsc.setOnClickListener {
            d("sorter", "asc")
            tvNewest.isSelected = false
            tvSortBy.isSelected = true
            tvAsc.isSelected = true
            tvDesc.isSelected = false
            controller.fetchProductByCategory(Category(catID, "pff"), 1)
        }
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
