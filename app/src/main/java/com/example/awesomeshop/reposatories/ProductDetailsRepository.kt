package com.example.awesomeshop.reposatories

import com.example.awesomeshop.models.product.ProductsResponseItem
import com.example.awesomeshop.networks.ApiClient
import com.example.awesomeshop.networks.ApiInterface
import javax.inject.Inject

class ProductDetailsRepository @Inject constructor(
    private val productApi: ApiInterface
) {
    suspend fun getProductDetails(id: Int): ProductsResponseItem? {
        return productApi.getProductDetails(id).body()
    }
}
