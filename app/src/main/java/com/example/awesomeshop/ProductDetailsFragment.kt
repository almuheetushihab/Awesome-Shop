package com.example.awesomeshop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.awesomeshop.databinding.ProductsDetailsBinding
import com.example.awesomeshop.reposatories.ProductDetailsRepository
import com.example.awesomeshop.sharedPreference.SharedPreferenceHelper
import com.example.awesomeshop.viewModel.ProductDetailsViewModel

class ProductDetailsFragment : Fragment() {
    private lateinit var binding: ProductsDetailsBinding
    private val args: ProductDetailsFragmentArgs by navArgs()
    private lateinit var viewModel: ProductDetailsViewModel
    private lateinit var sharedPreferences : SharedPreferenceHelper

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
            when(item.itemId){
                R.id.action_cart -> {
                    val action = ProductDetailsFragmentDirections.actionProductDetailsFragmentToCartsFragment(1)
                    findNavController().navigate(action)
                    true
                }
                R.id.action_logout ->{
                    logout()
                    true
                }
                else -> false
            }
        }

        val id = args.data
        viewModel = ProductDetailsViewModel(ProductDetailsRepository())
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

            }
        }

    }
    private fun logout() {
        sharedPreferences = SharedPreferenceHelper(requireContext())
        sharedPreferences.clearCredentials()
        val action = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
        findNavController().navigate(action)
    }

}