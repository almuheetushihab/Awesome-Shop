package com.example.awesomeshop.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.awesomeshop.models.cart.CartResponse
import com.example.awesomeshop.models.product.ProductsResponseItem
import com.example.awesomeshop.reposatories.CartRepository
import kotlinx.coroutines.launch





//class CartViewModel(private val cartRepository: CartRepository) : ViewModel() {
//
//    private val _cartData = MutableLiveData<CartResponse>()
//    val cartData: LiveData<CartResponse> = _cartData
//
//    fun fetchCart(cartId: Int) {
//        viewModelScope.launch {
//            val response = cartRepository.getCart(cartId)
//            if (response.isSuccessful) {
//                _cartData.value = response.body()
//            }
//        }
//    }
//}











class CartViewModel(private val repository: CartRepository) : ViewModel() {

    val cartItems = MutableLiveData<List<ProductsResponseItem>>()

    fun fetchCartData(cartId: Int) {
        viewModelScope.launch {
            val response = repository.getCart(cartId)
            if (response.isSuccessful) {
                val cartResponse = response.body()

                cartResponse?.let { cart ->
                    val productList = ArrayList<ProductsResponseItem>()

                    for (cartProduct in cart.products) {
                        val productResponse = repository.getProductById(cartProduct.productId)
                        if (productResponse.isSuccessful) {
                            productResponse.body()?.let { product ->
                                productList.add(product)
                            }
                        }
                    }

                    cartItems.postValue(productList)
                }
            }
        }
    }
}