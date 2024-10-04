package com.example.awesomeshop

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.awesomeshop.databinding.FragmentCartsListBinding
import com.example.awesomeshop.reposatories.CartRepository
import com.example.awesomeshop.sharedPreference.SharedPreferenceHelper
import com.example.awesomeshop.viewModel.CartViewModel


class CartsFragment : Fragment() {
    private lateinit var binding: FragmentCartsListBinding
    private lateinit var cartsAdapter: CartsAdapter
    private lateinit var viewModel: CartViewModel
    private val args: CartsFragmentArgs by navArgs()
    private lateinit var sharedPreferences: SharedPreferenceHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cartToolBer.toolBerTitle.text = "Carts"
        binding.cartToolBer.toolBerBackBtn.visibility = View.VISIBLE
        binding.cartToolBer.toolBerBackBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.cartToolBer.root.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_cart -> {
                    val action = CartsFragmentDirections.actionCartsFragmentToLoginFragment()
                    findNavController().navigate(action)
                    true
                }

                R.id.action_logout -> {
                    logout()
                    true
                }

                else -> false
            }
        }
        val recyclerView: RecyclerView = binding.cartRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        cartsAdapter = CartsAdapter()
        viewModel = CartViewModel(CartRepository())
        val cartRepository = CartRepository()
        viewModel = CartViewModel(cartRepository)
        val cartId = args.data
        Log.d("cartId", "onViewCreated: $cartId")
        viewModel.cartData(4)
        viewModel.items.observe(viewLifecycleOwner) {
            it?.let {
                Log.d("data", "onViewCreated: $it")
                cartsAdapter.setValues(it)
                recyclerView.adapter = cartsAdapter
                Log.d("cart", "onViewCreated: $it")
            }
        }

        binding.tvKey.text = "Total Price :"
        binding.tvValue.text = "200tk"

    }

    private fun logout() {
        sharedPreferences = SharedPreferenceHelper(requireContext())
        sharedPreferences.clearCredentials()
        val action = CartsFragmentDirections.actionCartsFragmentToLoginFragment()
        findNavController().navigate(action)


    }
}