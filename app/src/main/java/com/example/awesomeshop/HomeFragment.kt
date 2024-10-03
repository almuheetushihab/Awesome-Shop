package com.example.awesomeshop

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.awesomeshop.databinding.FragmentHomeBinding
import com.example.awesomeshop.reposatories.CategoriesRepository
import com.example.awesomeshop.reposatories.ProductRepository
import com.example.awesomeshop.sharedPreference.SharedPreferenceHelper
import com.example.awesomeshop.viewModel.CategoriesViewModel
import com.example.awesomeshop.viewModel.ProductViewModel


class HomeFragment : Fragment(), CategoriesAdapter.ItemClickListener,
    ProductAdapter.OnItemClickListener {
    private lateinit var binding: FragmentHomeBinding
    private val args: HomeFragmentArgs by navArgs()
    private lateinit var viewModel: CategoriesViewModel
    private lateinit var productViewModel: ProductViewModel
    private lateinit var adapter: CategoriesAdapter
    private lateinit var productAdapter: ProductAdapter
    private lateinit var sharedPreferences : SharedPreferenceHelper
    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeToolBer.toolBerTitle.text = "Awesome Shop"
        binding.homeToolBer.toolBerBackBtn.visibility = View.GONE

        sharedPreferences = SharedPreferenceHelper(requireContext())

        val fullName = args.data
        binding.tvWelcome.text = "Welcome, $fullName"

        val recyclerView: RecyclerView = binding.rvCategories
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val productRecyclerView: RecyclerView = binding.rvProducts
        productRecyclerView.layoutManager = GridLayoutManager(context, 2)

        CategoriesAdapter.listener = this
        ProductAdapter.listener = this


        viewModel = CategoriesViewModel(CategoriesRepository())

        viewModel.getCategories()
        viewModel.items.observe(viewLifecycleOwner) {
            it?.let {
                adapter = CategoriesAdapter()
                adapter.setCategoryList(it)
                recyclerView.adapter = adapter
                Log.d("categories", "onViewCreated: $it")

            }
        }

        productViewModel = ProductViewModel(ProductRepository())
        productViewModel.getProducts()
        productViewModel.items.observe(viewLifecycleOwner) {
            it?.let {
                productAdapter = ProductAdapter()
                productAdapter.setProductList(it)
                productRecyclerView.adapter = productAdapter
                Log.d("products", "onViewCreated: $it")
            }


        }


    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolber_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_cart -> {
               val action = HomeFragmentDirections.actionHomeFragmentToCartFragment()
                findNavController().navigate(action)
                true
            }

            R.id.action_logout -> {
                logout()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }


    private fun logout() {
    sharedPreferences.clearCredentials()
    val action = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
        findNavController().navigate(action)
}


    override fun onItemClick(categories: String) {
        val action =
            HomeFragmentDirections.actionHomeFragmentToCategoryWiseProductFragment(categories)
        findNavController().navigate(action)
    }

    override fun itemClick(id: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToProductDetailsFragment(id)
        findNavController().navigate(action)

    }

}

