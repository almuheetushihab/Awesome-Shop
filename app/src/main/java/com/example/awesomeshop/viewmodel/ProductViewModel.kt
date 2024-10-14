package com.example.awesomeshop.viewmodel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.awesomeshop.models.product.ProductsResponse
import com.example.awesomeshop.reposatories.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val productRepository: ProductRepository) : ViewModel() {

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