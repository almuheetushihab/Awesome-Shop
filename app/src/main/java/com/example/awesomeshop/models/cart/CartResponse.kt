package com.example.awesomeshop.models.cart

import java.io.Serializable

data class CartResponse(
    val __v: Int,
    val date: String,
    val id: Int,
    val products: List<Product>,
    val userId: Int
): Serializable