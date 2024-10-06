package com.example.awesomeshop.reposatories

import com.example.awesomeshop.models.product.ProductsResponse
import com.example.awesomeshop.networks.ApiClient
import com.example.awesomeshop.networks.ApiInterface


class ProductRepository {
    suspend fun getProducts(): ProductsResponse? {
        val productApi = ApiClient.getInstance().create(ApiInterface::class.java)
        return productApi.getProducts().body()
    }
}