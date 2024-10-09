package com.example.awesomeshop.reposatories

import com.example.awesomeshop.models.product.ProductsResponseItem
import com.example.awesomeshop.networks.ApiClient
import com.example.awesomeshop.networks.ApiInterface
import javax.inject.Inject

class ProductDetailsRepository @Inject constructor() {
    suspend fun getProductDetails(id: Int): ProductsResponseItem? {
        val productApi = ApiClient.getInstance().create(ApiInterface::class.java)
        return productApi.getProductDetails(id).body()
    }
}
