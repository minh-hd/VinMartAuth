package com.fpt.vinmartauth.view.productview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.fpt.vinmartauth.R

class ViewByCategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_by_category)
        findViewById<TextView>(R.id.tvCategoryTitle).text = intent.getStringExtra("CategoryName")
    }
}