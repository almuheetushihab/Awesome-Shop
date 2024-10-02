package com.example.awesomeshop.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.awesomeshop.models.product.ProductsResponse
import com.example.awesomeshop.reposatories.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(val productRepository: ProductRepository) : ViewModel() {


    private val _items: MutableLiveData<ProductsResponse?> by lazy {
        MutableLiveData<ProductsResponse?>()
    }
    val items: MutableLiveData<ProductsResponse?> = _items

    fun getProducts() = viewModelScope.launch {
        try {
            _items.value = productRepository.getProducts()
        } catch (e: Exception) {
            _items.value = null
        }

    }
}