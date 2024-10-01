package com.example.awesomeshop.networks

import com.example.awesomeshop.models.product.ProductsResponse
import com.example.awesomeshop.models.product.ProductsResponseItem
import retrofit2.Response
import retrofit2.http.GET


interface ApiInterface {
    @GET("products")
    suspend fun getProducts(): Response<ProductsResponse>
}