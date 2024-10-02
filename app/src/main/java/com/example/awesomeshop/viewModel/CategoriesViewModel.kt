package com.example.awesomeshop.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.awesomeshop.models.product.ProductsResponse
import com.example.awesomeshop.reposatories.CategoriesRepository
import kotlinx.coroutines.launch

class CategoriesViewModel(val categoriesRepository: CategoriesRepository) : ViewModel() {
    private val _items: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>()
    }
    val items: MutableLiveData<List<String>> = _items
    fun getCategories() = viewModelScope.launch {
        try {
            _items.value = categoriesRepository.getCategories()
        } catch (e: Exception) {
            _items.value = null
        }
    }
}