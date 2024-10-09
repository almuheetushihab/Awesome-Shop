package com.example.awesomeshop.reposatories

import com.example.awesomeshop.models.login.LoginRequest
import com.example.awesomeshop.models.login.LoginResponse
import com.example.awesomeshop.networks.ApiInterface
import retrofit2.Response
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val loginInterface: ApiInterface
) {

    suspend fun login(username: String, password: String): Response<LoginResponse> {
        return loginInterface.login(LoginRequest(username, password))
    }
}