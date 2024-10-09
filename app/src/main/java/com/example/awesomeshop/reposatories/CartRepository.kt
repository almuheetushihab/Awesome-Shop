package com.example.awesomeshop.reposatories

import com.example.awesomeshop.models.cart.CartResponse
import com.example.awesomeshop.models.product.ProductsResponseItem
import com.example.awesomeshop.networks.ApiClient
import com.example.awesomeshop.networks.ApiInterface
import retrofit2.Response
import javax.inject.Inject

class CartRepository @Inject constructor() {

    private val apiInterface: ApiInterface = ApiClient.getInstance().create(ApiInterface::class.java)

    suspend fun getCart(cartId: Int): Response<CartResponse> {
        return apiInterface.getCart(cartId)
    }

    suspend fun getProductById(productId: Int): Response<ProductsResponseItem> {
        return apiInterface.getProductDetails(productId)
    }
}
