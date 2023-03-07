package com.example.kotkin_team.products.data

import androidx.recyclerview.widget.RecyclerView

// какие-то данные со страниц
internal data class ProductsPage (
    val title: String,
    val subTitle: String, // Молоко и молочные продукты
    val backButton: Boolean,
    val searchButton: Boolean,
    val recyclerView: RecyclerView,
)