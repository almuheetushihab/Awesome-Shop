package com.example.awesomeshop.reposatories

import com.example.awesomeshop.networks.ApiClient
import com.example.awesomeshop.networks.ApiInterface

class CategoriesRepository {
    suspend fun getCategories(): List<String>? {
        val productApi = ApiClient.getInstance().create(ApiInterface::class.java)
        return productApi.getCategories().body()
    }
}