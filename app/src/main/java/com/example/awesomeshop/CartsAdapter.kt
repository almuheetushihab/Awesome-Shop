package com.example.awesomeshop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.awesomeshop.databinding.AdapterCartsBinding
import com.example.awesomeshop.models.product.ProductsResponseItem

class CartsAdapter : RecyclerView.Adapter<CartsAdapter.ViewHolder>() {
    private var cartList = ArrayList<ProductsResponseItem>()

    class ViewHolder(val binding: AdapterCartsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterCartsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = cartList[position]

        viewHolder.binding.tvProductName.text = item.title
        viewHolder.binding.tvPriceValue.text = "${item.price} tk"
        viewHolder.binding.tvQuantityValue.text = "Quantity: ${item.rating.count}"
        viewHolder.binding.tvTotalValue.text = "${item.price * item.rating.count} tk"
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    fun setValues(products: List<ProductsResponseItem>) {
        this.cartList = ArrayList(products)
        notifyDataSetChanged()
    }
}
