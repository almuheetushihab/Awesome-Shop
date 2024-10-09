package com.example.awesomeshop.reposatories

import com.example.awesomeshop.networks.ApiClient
import com.example.awesomeshop.networks.ApiInterface
import javax.inject.Inject

class CategoriesRepository @Inject constructor(
    private val productApi: ApiInterface
) {

    suspend fun getCategories(): List<String>? {
        return productApi.getCategories().body()
    }
}