package com.example.awesomeshop

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.awesomeshop.databinding.FragmentHomeBinding
import com.example.awesomeshop.reposatories.CategoriesRepository
import com.example.awesomeshop.reposatories.ProductRepository
import com.example.awesomeshop.sharedPreference.SharedPreferenceHelper
import com.example.awesomeshop.viewModel.CategoriesViewModel
import com.example.awesomeshop.viewModel.ProductViewModel


class HomeFragment : Fragment(), CategoriesAdapter.ItemClickListener, ProductAdapter.OnItemClickListener {
    private lateinit var binding: FragmentHomeBinding
    private val args: HomeFragmentArgs by navArgs()
    private lateinit var viewModel: CategoriesViewModel
    private lateinit var productViewModel: ProductViewModel
    private lateinit var adapter: CategoriesAdapter
    private lateinit var productAdapter: ProductAdapter
    private lateinit var sharedPreferences : SharedPreferenceHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        sharedPreferences = SharedPreferenceHelper(requireContext())
        setHasOptionsMenu(false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loadingId.root.visibility = View.VISIBLE
        binding.rvCategories.visibility = View.GONE
        binding.rvProducts.visibility = View.GONE
        binding.tvWelcome.visibility = View.GONE
        binding.tvCategories.visibility = View.GONE
        binding.tvProducts.visibility = View.GONE

        fetchData()

        binding.homeToolBer.root.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_cart -> {
                    val cartId = 1
                    val action = HomeFragmentDirections.actionHomeFragmentToCartsFragment(cartId)
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

        binding.homeToolBer.toolBerTitle.text = "Awesome Shop"
        binding.homeToolBer.toolBerBackBtn.visibility = View.GONE

        sharedPreferences = SharedPreferenceHelper(requireContext())

        val fullName = args.data
        binding.tvWelcome.text = "Welcome, $fullName"
        val prefFullName = sharedPreferences.getFullName()
        binding.tvWelcome.text = "Welcome, $prefFullName"

        val recyclerView: RecyclerView = binding.rvCategories
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val productRecyclerView: RecyclerView = binding.rvProducts
        productRecyclerView.layoutManager = GridLayoutManager(context, 2)

        CategoriesAdapter.listener = this
        ProductAdapter.listener = this
    }

    private fun fetchData() {
        viewModel = CategoriesViewModel(CategoriesRepository())
        viewModel.getCategories()
        viewModel.items.observe(viewLifecycleOwner) {
            it?.let {
                adapter = CategoriesAdapter()
                adapter.setCategoryList(it)
                binding.rvCategories.adapter = adapter
                binding.rvCategories.visibility = View.VISIBLE
                checkDataLoad()
                Log.d("categories", "onViewCreated: $it")
            }
        }

        productViewModel = ProductViewModel(ProductRepository())
        productViewModel.getProducts()
        productViewModel.items.observe(viewLifecycleOwner) {
            it?.let {
                productAdapter = ProductAdapter()
                productAdapter.setProductList(it)
                binding.rvProducts.adapter = productAdapter
                binding.rvProducts.visibility = View.VISIBLE
                checkDataLoad()
                Log.d("products", "onViewCreated: $it")
            }
        }
    }

    private fun checkDataLoad() {
        if (binding.rvCategories.visibility == View.VISIBLE && binding.rvProducts.visibility == View.VISIBLE) {
            binding.loadingId.root.visibility = View.GONE
            binding.tvWelcome.visibility = View.VISIBLE
            binding.tvCategories.visibility = View.VISIBLE
            binding.tvProducts.visibility = View.VISIBLE
        }
    }

    private fun logout() {
        sharedPreferences.clearCredentials()
        val action = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
        findNavController().navigate(action)
    }

    override fun onItemClick(category: String) {
        val action =
            HomeFragmentDirections.actionHomeFragmentToCategoryWiseProductFragment(category)
        findNavController().navigate(action)
    }

    override fun itemClick(product: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToProductDetailsFragment(product)
        findNavController().navigate(action)
    }
}
