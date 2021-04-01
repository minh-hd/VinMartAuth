package com.fpt.vinmartauth.adapter

import android.content.Intent
import com.fpt.vinmartauth.entity.ProfileMenuItem
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.fpt.vinmartauth.R
import android.widget.TextView
import com.fpt.vinmartauth.view.OrderHistoryActivity
import java.util.ArrayList

class ProfileMenuItemAdapter(i: ArrayList<ProfileMenuItem>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = ArrayList<ProfileMenuItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_profile_menu_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(items[position])
        if(items[position].itemName.equals("Lịch sử đơn hàng")){
            (holder as ViewHolder).itemView.setOnClickListener { v ->
                val intent = Intent(v.context, OrderHistoryActivity::class.java)
                v.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val itemName: TextView = view.findViewById(R.id.tvProfileMenuItemName)
        private val itemIcon: ImageView = view.findViewById(R.id.ivProfileMenuItemIcon)
        fun bind(item: ProfileMenuItem) {
            itemName.text = item.itemName
            itemIcon.setImageResource(item.iconID)
        }

    }

    init {
        if (i != null) {
            items.addAll(i)
        }
    }

}