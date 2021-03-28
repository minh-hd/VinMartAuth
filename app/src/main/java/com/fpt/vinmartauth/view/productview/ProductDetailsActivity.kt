package com.fpt.vinmartauth.view.productview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.fpt.vinmartauth.R
import com.fpt.vinmartauth.entity.Product
import com.squareup.picasso.Picasso

class ProductDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        val product = Product(
                intent.getStringExtra("ID"),
                intent.getStringExtra("category"),
                intent.getStringExtra("description"),
                intent.getStringExtra("image"),
                intent.getIntExtra("price", 0),
                intent.getStringExtra("quantity"),
                intent.getStringExtra("title"),
                intent.getStringExtra("vendor")
        )
        val productImg = findViewById<ImageView>(R.id.ivProductDetail)
        findViewById<TextView>(R.id.tvProductDetailTitle).text = product.title
        findViewById<TextView>(R.id.tvProductDetailDescription).text = product.description
        findViewById<TextView>(R.id.tvProductDetailPrice).text = product.price.toString() + " đ"
        Picasso.get().load(product.image).into(productImg)


    }
}