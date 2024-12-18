package com.example.awesomeshop.productdetailsscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.awesomeshop.R
import com.example.awesomeshop.databinding.ProductsDetailsBinding
import com.example.awesomeshop.sharedpreference.SharedPreferenceHelper
import com.example.awesomeshop.viewmodel.ProductDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {
    private lateinit var binding: ProductsDetailsBinding
    private val args: ProductDetailsFragmentArgs by navArgs()
    private val viewModel: ProductDetailsViewModel by viewModels()
    private lateinit var sharedPreferences: SharedPreferenceHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProductsDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.productToolBer.toolBerTitle.text = "Product Details"
        binding.productToolBer.toolBerBackBtn.visibility = View.VISIBLE
        binding.productToolBer.toolBerBackBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.productToolBer.root.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_cart -> {
                    val action =
                        ProductDetailsFragmentDirections.actionProductDetailsFragmentToCartsFragment(
                            1
                        )
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

        binding.productDetailsLoading.root.visibility = View.VISIBLE

        val id = args.data
        viewModel.getProductDetails(id)
        viewModel.items.observe(viewLifecycleOwner) {
            it?.let {
                val imageUrl = it.image
                Glide.with(binding.root.context)
                    .load(imageUrl)
                    .into(binding.productImg)
                binding.productTitle.text = "Title : ${it.title}"
                binding.productPrice.text = "Price : ${it.price}৳"
                binding.productDescription.text = "Description : ${it.description}"
                binding.tvKey.text = it.category
                binding.tvValue.text = "Rating : ${it.rating.rate}%"
                binding.productDetailsLoading.root.visibility = View.GONE

            }
        }
    }

    private fun logout() {
        sharedPreferences = SharedPreferenceHelper(requireContext())
        sharedPreferences.clearCredentials()
        val action = ProductDetailsFragmentDirections.actionProductDetailsFragmentToLoginFragment()
        findNavController().navigate(action)

    }
}