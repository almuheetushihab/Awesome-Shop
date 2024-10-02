package com.example.awesomeshop.reposatories

import com.example.awesomeshop.models.product.ProductsResponseItem
import com.example.awesomeshop.networks.ApiClient
import com.example.awesomeshop.networks.ApiInterface

class ProductDetailsRepository {
    suspend fun getProductDetails(id: Int): ProductsResponseItem? {
        val productApi = ApiClient.getInstance().create(ApiInterface::class.java)
        return productApi.getProductDetails(id).body()
    }
}
