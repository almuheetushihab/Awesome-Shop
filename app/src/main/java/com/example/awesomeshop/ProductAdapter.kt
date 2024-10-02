package com.example.awesomeshop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.awesomeshop.databinding.ProductAdapterBinding
import com.example.awesomeshop.models.product.ProductsResponseItem

import java.util.ArrayList

class ProductAdapter() : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    private  var productList = ArrayList<ProductsResponseItem>()

    companion object{
        var listener: ItemClickListener? = null
    }

    class ViewHolder(var binding: ProductAdapterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int):ViewHolder {
        val binding = ProductAdapterBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val product = productList[position]
        viewHolder.binding.singleProductPrice.text = product.price.toString()
        viewHolder.binding.singleProductTitle.text = product.title
//        viewHolder.binding.singleProductImg.setImageResource(product.image.length)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun setProductList(product: ArrayList<ProductsResponseItem>) {
        this.productList = ArrayList(product)
        notifyDataSetChanged()
    }

    interface ItemClickListener {
        fun onItemClick(position: Int)
    }
}

