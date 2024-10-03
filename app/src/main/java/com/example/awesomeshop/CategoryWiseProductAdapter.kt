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

    class ViewHolder(val binding: CategoryWiseProductAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = CategoryWiseProductAdapterBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val product = productList[position]

        val imageUrl = product.image

        Glide.with(viewHolder.itemView.context)
            .load(imageUrl)
            .into(viewHolder.binding.productImg)

        viewHolder.binding.productTitle.text = "Title: ${product.title}"
        viewHolder.binding.productPrice.text = "Price: ${product.price}"
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun setProductList(products: List<ProductsResponseItem>) {
        this.productList = ArrayList(products)
        notifyDataSetChanged()
    }
}
