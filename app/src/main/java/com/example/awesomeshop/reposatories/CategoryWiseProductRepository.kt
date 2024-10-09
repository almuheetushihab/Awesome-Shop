package com.example.awesomeshop.reposatories

import com.example.awesomeshop.models.product.ProductsResponseItem
import com.example.awesomeshop.networks.ApiClient
import com.example.awesomeshop.networks.ApiInterface
import javax.inject.Inject

class CategoryWiseProductRepository @Inject constructor(
    private val categoriesProductApi: ApiInterface
) {

    suspend fun getCategoryWiseProducts(category: String): List<ProductsResponseItem>? {
        return categoriesProductApi.getCategoryWiseProducts(category).body()
    }
}
