package com.fpt.vinmartauth.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fpt.vinmartauth.R
import com.fpt.vinmartauth.adapter.OrderHistoryAdapter
import com.fpt.vinmartauth.entity.Order
import com.fpt.vinmartauth.view.controller.OrderController

class OrderHistoryActivity : AppCompatActivity(), OrderView {
    val controller = OrderController();
    var adapter = OrderHistoryAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_history)
        val recyclerView = findViewById<RecyclerView>(R.id.rvOrderHistory)
        recyclerView.adapter = adapter
        controller.setView(this)
        controller.getAllOrder()
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun setOrders(orders: MutableList<Order>?) {
        if (orders != null) {
            adapter.setData(orders)
            Log.d("kienct", orders.size.toString())
        }
    }
}