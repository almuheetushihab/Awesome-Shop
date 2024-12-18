package com.example.awesomeshop.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.awesomeshop.models.product.ProductsResponseItem
import com.example.awesomeshop.reposatories.CategoryWiseProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryWiseProductViewModel @Inject constructor(
    private val categoryWiseProductRepository: CategoryWiseProductRepository
) : ViewModel() {

    private val _items = MutableLiveData<List<ProductsResponseItem>?>()
    val items: LiveData<List<ProductsResponseItem>?> = _items

    fun getCategoryWiseProducts(category: String) = viewModelScope.launch {
        try {
            _items.value = categoryWiseProductRepository.getCategoryWiseProducts(category)
        } catch (e: Exception) {
            _items.value = null
        }
    }
}
