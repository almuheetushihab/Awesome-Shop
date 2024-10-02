package com.example.awesomeshop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.awesomeshop.databinding.ProductAdapterBinding
import com.example.awesomeshop.models.product.ProductsResponseItem

import java.util.ArrayList

class ProductAdapter() : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    private  var productList = ArrayList<ProductsResponseItem>()

    companion object{
        var listener: OnItemClickListener? = null
    }

    class ViewHolder(var binding: ProductAdapterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int):ViewHolder {
        val binding = ProductAdapterBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val product = productList[position]

        val imageUrl = product.image
        Glide.with(viewHolder.itemView.context)
            .load(imageUrl)
            .into(viewHolder.binding.singleProductImg)

        viewHolder.binding.singleProductTitle.text = "Title : ${product.title}"
        viewHolder.binding.singleProductPrice.text = "Price : ${product.price}৳"
        viewHolder.binding.singleRating.text = "Rating : ${product.rating.rate}%"
        viewHolder.itemView.setOnClickListener {
            listener?.itemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun setProductList(product: ArrayList<ProductsResponseItem>) {
        this.productList = ArrayList(product)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun itemClick(position: Int)
    }
}

