package com.example.awesomeshop.reposatories

import com.example.awesomeshop.models.product.ProductsResponseItem
import com.example.awesomeshop.networks.ApiClient
import com.example.awesomeshop.networks.ApiInterface
import javax.inject.Inject

class CategoryWiseProductRepository @Inject constructor() {
    suspend fun getCategoryWiseProducts(category: String): List<ProductsResponseItem>? {
        val categoriesProductApi = ApiClient.getInstance().create(ApiInterface::class.java)
        return categoriesProductApi.getCategoryWiseProducts(category).body()
    }
}
