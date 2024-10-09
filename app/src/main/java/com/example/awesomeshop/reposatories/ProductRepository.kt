package com.example.awesomeshop.reposatories

import com.example.awesomeshop.models.product.ProductsResponse
import com.example.awesomeshop.networks.ApiClient
import com.example.awesomeshop.networks.ApiInterface
import javax.inject.Inject


class ProductRepository @Inject constructor() {
    suspend fun getProducts(): ProductsResponse? {
        val productApi = ApiClient.getInstance().create(ApiInterface::class.java)
        return productApi.getProducts().body()
    }
}