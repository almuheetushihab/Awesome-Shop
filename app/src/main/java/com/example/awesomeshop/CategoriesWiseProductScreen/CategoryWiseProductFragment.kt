package com.example.awesomeshop.CategoriesWiseProductScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.awesomeshop.R
import com.example.awesomeshop.databinding.FragmentCategoryWiseProductListBinding
import com.example.awesomeshop.sharedPreference.SharedPreferenceHelper
import com.example.awesomeshop.viewModel.CategoryWiseProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CategoryWiseProductFragment : Fragment() {
    private lateinit var binding: FragmentCategoryWiseProductListBinding

    @Inject
    lateinit var categoryWiseProductAdapter: CategoryWiseProductAdapter
    private val viewModel: CategoryWiseProductViewModel by viewModels()
    private val args: CategoryWiseProductFragmentArgs by navArgs()
    private lateinit var sharedPreferences : SharedPreferenceHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryWiseProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.categoryWiseProductFragmentToolBer.toolBerTitle.text = "Category Wise Product"
        binding.categoryWiseProductFragmentToolBer.toolBerBackBtn.visibility = View.VISIBLE
        binding.categoryWiseProductFragmentToolBer.toolBerBackBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }
        sharedPreferences = SharedPreferenceHelper(requireContext())

        binding.categoryWiseProductFragmentToolBer.root.setOnMenuItemClickListener { item ->
            when(item.itemId){
                R.id.action_cart -> {
                    val action = CategoryWiseProductFragmentDirections.actionCategoryWiseProductFragmentToCartsFragment(1)
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


        binding.loadingView.root.visibility = View.VISIBLE
        val category = args.data
        val recyclerView: RecyclerView = binding.categoryWiseProductFragmentRecyclerView
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        categoryWiseProductAdapter = CategoryWiseProductAdapter()

        viewModel.getCategoryWiseProducts(category)
        viewModel.items.observe(viewLifecycleOwner) {
            it?.let {
                categoryWiseProductAdapter.setProductList(it)
                recyclerView.adapter = categoryWiseProductAdapter
                binding.loadingView.root.visibility = View.GONE
            }
        }
    }
    private fun logout() {
        sharedPreferences = SharedPreferenceHelper(requireContext())
        sharedPreferences.clearCredentials()
        val action = CategoryWiseProductFragmentDirections.actionCategoryWiseProductFragmentToLoginFragment()
        findNavController().navigate(action)
    }
}
