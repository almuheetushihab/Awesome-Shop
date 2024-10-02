package com.example.awesomeshop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.awesomeshop.databinding.CategoryWiseProductAdapterBinding
import com.example.awesomeshop.models.product.ProductsResponse
import com.example.awesomeshop.models.product.ProductsResponseItem

class CategoryWiseProductAdapter : RecyclerView.Adapter<CategoryWiseProductAdapter.ViewHolder>() {

    private var productList = ArrayList<ProductsResponseItem>()

    class ViewHolder(val binding: CategoryWiseProductAdapterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = CategoryWiseProductAdapterBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val product = productList[position]

        Glide.with(viewHolder.itemView.context)
            .load(product.image)
            .into(viewHolder.binding.productImg)

        viewHolder.binding.productTitle.text = product.title
        viewHolder.binding.productPrice.text = product.price.toString()
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun setProductList(products: List<ProductsResponseItem>) {
        this.productList = ArrayList(products)
        notifyDataSetChanged()
    }
}
