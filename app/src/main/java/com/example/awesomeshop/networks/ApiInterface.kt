package com.example.awesomeshop.networks

import com.example.awesomeshop.models.login.LoginRequest
import com.example.awesomeshop.models.login.LoginResponse
import com.example.awesomeshop.models.product.ProductsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST



interface ApiInterface {

    @GET("products/categories")
    suspend fun getCategories(): Response<List<String>>

    @GET("products")
    suspend fun getProducts(): Response<ProductsResponse>

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

}