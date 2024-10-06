package com.example.awesomeshop.reposatories

import com.example.awesomeshop.models.product.ProductsResponseItem
import com.example.awesomeshop.networks.ApiClient
import com.example.awesomeshop.networks.ApiInterface

class CategoryWiseProductRepository {
    suspend fun getCategoryWiseProducts(category: String): List<ProductsResponseItem>? {
        val categoriesProductApi = ApiClient.getInstance().create(ApiInterface::class.java)
        return categoriesProductApi.getCategoryWiseProducts(category).body()
    }
}
