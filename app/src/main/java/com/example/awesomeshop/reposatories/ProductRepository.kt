package com.example.awesomeshop.reposatories

import com.example.awesomeshop.models.product.ProductsResponse
import com.example.awesomeshop.networks.ApiClient
import com.example.awesomeshop.networks.ApiInterface
import javax.inject.Inject


class ProductRepository @Inject constructor(
    private val productApi: ApiInterface
) {
    suspend fun getProducts(): ProductsResponse? {
        return productApi.getProducts().body()
    }
}