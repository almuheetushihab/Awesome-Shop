package com.example.awesomeshop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.awesomeshop.databinding.CategoriesAdapterBinding
import java.util.ArrayList

class CategoriesAdapter() : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    private var categoryList = ArrayList<String>()

    companion object{
        lateinit var listener: ItemClickListener
    }

    class ViewHolder(val binding: CategoriesAdapterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = CategoriesAdapterBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val category = categoryList[position]
        viewHolder.binding.categoriesName.text = category
        viewHolder.itemView.setOnClickListener {
            listener.onItemClick(category)
        }
    }

    override fun getItemCount(): Int = categoryList.size

    fun setCategoryList(categories: List<String>) {
        categoryList = ArrayList(categories)
        notifyDataSetChanged()
    }

    interface ItemClickListener {
        fun onItemClick(category: String)
    }
}
