package com.example.awesomeshop.cartsscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.awesomeshop.R
import com.example.awesomeshop.databinding.FragmentCartsListBinding
import com.example.awesomeshop.sharedpreference.SharedPreferenceHelper
import com.example.awesomeshop.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CartsFragment : Fragment(), CartsAdapter.TotalPriceUpdater {
    private lateinit var binding: FragmentCartsListBinding
    @Inject
    lateinit var cartsAdapter: CartsAdapter
    private val viewModel: CartViewModel by viewModels()
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
        binding.cartToolBer.root.menu?.findItem(R.id.action_cart)?.isVisible = false
        binding.cartToolBer.root.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_logout -> {
                    logout()
                    true
                }

                else -> false
            }
        }


        binding.tvKey.visibility = View.GONE
        binding.tvValue.visibility = View.GONE
        binding.orderBtn.visibility = View.GONE

        val recyclerView: RecyclerView = binding.cartRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        cartsAdapter = CartsAdapter(this)

        val cartId = args.cartId
        binding.cartLoading.root.visibility = View.VISIBLE
        viewModel.cartData(cartId)
        viewModel.items.observe(viewLifecycleOwner) { items ->
            binding.cartLoading.root.visibility = View.GONE
            items?.let {
                if (it.isNotEmpty()) {
                    cartsAdapter.setValues(it)
                    recyclerView.adapter = cartsAdapter
                    binding.cartRecyclerView.visibility = View.VISIBLE

                    binding.tvKey.visibility = View.VISIBLE
                    binding.tvValue.visibility = View.VISIBLE
                    binding.orderBtn.visibility = View.VISIBLE
//                    val totalPrice = cartsAdapter.getTotalPrice()
//                    updateTotalPrice(totalPrice)
                    binding.tvKey.text = "Total Price:"
                    binding.tvValue.text = "46964.70 tk"


                } else {
                    binding.cartRecyclerView.visibility = View.GONE
                    binding.tvKey.visibility = View.GONE
                    binding.tvValue.visibility = View.GONE
                    binding.orderBtn.visibility = View.GONE
                }
            }
        }

        binding.orderBtn.setOnClickListener {
            Toast.makeText(requireContext(), "Order Completed", Toast.LENGTH_SHORT).show()
            val action = CartsFragmentDirections.actionCartsFragmentToHomeFragment()
            findNavController().navigate(action)
        }
    }

    private fun logout() {
        sharedPreferences = SharedPreferenceHelper(requireContext())
        sharedPreferences.clearCredentials()
        val action = CartsFragmentDirections.actionCartsFragmentToLoginFragment()
        findNavController().navigate(action)
    }

    override fun updateTotalPrice(totalPrice: Double) {
        binding.tvValue.text = String.format("%.2f tk", totalPrice)
    }
}
