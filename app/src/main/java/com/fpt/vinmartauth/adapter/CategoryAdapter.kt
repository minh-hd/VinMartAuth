package com.fpt.vinmartauth.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fpt.vinmartauth.R
import com.fpt.vinmartauth.entity.CategoryIcon
import com.fpt.vinmartauth.view.by_category.ViewByCategoryActivity

class CategoryAdapter(private val list: List<CategoryIcon>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
                LayoutInflater.from(parent.context).inflate(R.layout.layout_category, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val icon = list[position]
        if (holder is ViewHolder) {
            holder.bind(icon)
            holder.itemView.setOnClickListener { v ->
                val intent = Intent(v.context, ViewByCategoryActivity::class.java)
                intent.putExtra("CategoryName", icon.catName)
                intent.putExtra("CategoryID", icon.catID)
                v.context.startActivity(intent)
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: CategoryIcon) {
            val name: TextView = itemView.findViewById(R.id.tvCatName)
            val imgSrc: ImageView = itemView.findViewById(R.id.ivCatIcon)

            name.text = item.catName
            imgSrc.setImageResource(item.imgSrc)

        }
    }
}