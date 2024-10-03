package com.example.awesomeshop

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import com.example.awesomeshop.placeholder.PlaceholderContent.PlaceholderItem
import com.example.awesomeshop.databinding.AdapterCartsBinding
import com.example.awesomeshop.models.product.ProductsResponseItem


class CartsAdapter : RecyclerView.Adapter<CartsAdapter.ViewHolder>() {
    private var values = ArrayList<ProductsResponseItem>()

    class ViewHolder(val binding: AdapterCartsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterCartsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = values[position]

    }

    override fun getItemCount(): Int {
        return values.size

    }
    fun setValues(products: List<ProductsResponseItem>) {
        this.values = ArrayList(values)
        notifyDataSetChanged()
    }


}