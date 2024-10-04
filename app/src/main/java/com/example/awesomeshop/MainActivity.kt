package com.example.awesomeshop

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.awesomeshop.databinding.ActivityMainBinding
import com.example.awesomeshop.reposatories.CartRepository
import com.example.awesomeshop.reposatories.CategoriesRepository
import com.example.awesomeshop.reposatories.CategoryWiseProductRepository
import com.example.awesomeshop.reposatories.ProductDetailsRepository
import com.example.awesomeshop.reposatories.ProductRepository
import com.example.awesomeshop.viewModel.CartViewModel
import com.example.awesomeshop.viewModel.CategoriesViewModel
import com.example.awesomeshop.viewModel.CategoryWiseProductViewModel
import com.example.awesomeshop.viewModel.ProductDetailsViewModel
import com.example.awesomeshop.viewModel.ProductViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: CartViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = CartViewModel(CartRepository())
        viewModel.fetchCartData(5)
        viewModel.cartItems.observe(this) {
            it?.let {
                Log.d("cart", "cart: $it")
            }

//        viewModel = ProductDetailsViewModel(ProductDetailsRepository())
//        viewModel.getProductDetails(1)
//        viewModel.items.observe(this) {
//            it?.let {
//                Log.d("product", "onCreate: $it")
//            }
//        }
        }
    }
}