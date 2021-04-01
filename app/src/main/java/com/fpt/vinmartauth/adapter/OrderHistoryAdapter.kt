package com.fpt.vinmartauth.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fpt.vinmartauth.R
import com.fpt.vinmartauth.entity.Order
import java.util.ArrayList

class OrderHistoryAdapter() :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val list = ArrayList<Order>()
    fun setData(orders: List<Order>){
        list.clear()
        list.addAll(orders)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
                LayoutInflater.from(parent.context).inflate(R.layout.layout_order_history, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val icon = list[position]
        if (holder is ViewHolder) {
            holder.bind(icon)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Order) {
            val tvOrder: TextView = itemView.findViewById(R.id.tvOrderID)
            val tvStatus: TextView = itemView.findViewById(R.id.tvStatusID)
            tvOrder.text = item.id
            tvStatus.text = item.statusID
            Log.d("kienct", item.toString())
        }
    }
}