package com.example.awesomeshop

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.awesomeshop.databinding.ActivityMainBinding
import com.example.awesomeshop.reposatories.CartRepository
import com.example.awesomeshop.sharedPreference.SharedPreferenceHelper
import com.example.awesomeshop.viewModel.CartViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var sharedPreferences: SharedPreferenceHelper

    private lateinit var viewModel: CartViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = CartViewModel(CartRepository())
        viewModel.cartData(5)
        viewModel.items.observe(this) {
            it?.let {
                Log.d("cart", "cart: $it")
            }
        }



        sharedPreferences = SharedPreferenceHelper(this)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        navController = navHostFragment.navController

        val savedUsername = sharedPreferences.getUsername()
        val savedPassword = sharedPreferences.getPassword()
        if (!savedUsername.isNullOrEmpty() && !savedPassword.isNullOrEmpty()) {
            navController.navigate(R.id.homeFragment)
        }else{
            navController.navigate(R.id.loginFragment)
        }

    }
}


