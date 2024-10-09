package com.example.awesomeshop.reposatories

import com.example.awesomeshop.models.login.LoginRequest
import com.example.awesomeshop.models.login.LoginResponse
import com.example.awesomeshop.networks.ApiClient
import com.example.awesomeshop.networks.ApiInterface
import retrofit2.Response
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val apiInterface: ApiInterface
) {

    suspend fun login(username: String, password: String): Response<LoginResponse> {
        return apiInterface.login(LoginRequest(username, password))
    }
}