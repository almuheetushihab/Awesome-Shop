package com.example.awesomeshop.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.awesomeshop.models.login.LoginResponse
import com.example.awesomeshop.reposatories.LoginRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val repository = LoginRepository()

    private val _items = MutableLiveData<Response<LoginResponse>>()
    val items: LiveData<Response<LoginResponse>> = _items

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val response = repository.login(username, password)
            _items.postValue(response)
        }
    }
}