package com.example.awesomeshop.models.cart

data class CartResponse(
    val __v: Int,
    val date: String,
    val id: Int,
    val products: List<Product>,
    val userId: Int
)