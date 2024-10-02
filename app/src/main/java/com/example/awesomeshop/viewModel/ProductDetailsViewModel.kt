package com.example.awesomeshop.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.awesomeshop.models.product.ProductsResponseItem
import com.example.awesomeshop.reposatories.ProductDetailsRepository
import kotlinx.coroutines.launch

class ProductDetailsViewModel(private val productDetailsRepository: ProductDetailsRepository) : ViewModel() {

    private val _items = MutableLiveData<ProductsResponseItem?>()
    val items: LiveData<ProductsResponseItem?> = _items

    fun getProductDetails(id: Int) = viewModelScope.launch {
        try {
            _items.value = productDetailsRepository.getProductDetails(id)
        } catch (e: Exception) {
            _items.value = null
        }
    }
}
