package com.example.awesomeshop

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.awesomeshop.databinding.FragmentCartsListBinding
import com.example.awesomeshop.placeholder.PlaceholderContent


class CartsFragment : Fragment() {
    private lateinit var binding: FragmentCartsListBinding
    private lateinit var cartsAdapter: CartsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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
        val recyclerView: RecyclerView = binding.cartRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        cartsAdapter = CartsAdapter()
        recyclerView.adapter = cartsAdapter
    }


}