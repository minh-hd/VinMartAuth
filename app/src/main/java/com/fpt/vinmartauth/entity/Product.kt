package com.fpt.vinmartauth.entity

data class Product(
        val description: String = "",
        val image: String = "",
        val price: Int = 0,
        val quantity: String = "",
        val title: String = "",
        val vendor: String = "",
        val category: Category
)