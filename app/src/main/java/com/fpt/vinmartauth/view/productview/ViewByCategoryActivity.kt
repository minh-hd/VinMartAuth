package com.fpt.vinmartauth.view.productview

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.fpt.vinmartauth.R
import com.fpt.vinmartauth.model.ProductModel

class ViewByCategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_by_category)
        findViewById<TextView>(R.id.tvCategoryTitle).text = intent.getStringExtra("CategoryName")
        val catID = intent.getStringExtra("CategoryID")
        val productModel = ProductModel()
        productModel.getProductByCategory(catID)
        findViewById<ImageView>(R.id.imageView5).setOnClickListener {
            finish()
        }
    }
}