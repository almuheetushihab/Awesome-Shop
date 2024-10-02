package com.example.awesomeshop.reposatories

import com.example.awesomeshop.models.login.LoginRequest
import com.example.awesomeshop.models.login.LoginResponse
import com.example.awesomeshop.networks.ApiClient
import com.example.awesomeshop.networks.ApiInterface
import retrofit2.Response

class LoginRepository {


    suspend fun login(username: String, password: String): Response<LoginResponse> {
        val loginApi = ApiClient.getInstance().create(ApiInterface::class.java)
        return loginApi.login(LoginRequest(username, password))
    }
}