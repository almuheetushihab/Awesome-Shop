package com.example.awesomeshop

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup

class CartAdapter() : RecyclerView.Adapter<CartAdapter.ViewHolder>() {
//    private val cartList = ArrayList<CartResponseItem>()

    companion object{}
    class ViewHolder(var binding: CartAdapterBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CartAdapter.ViewHolder {

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {

    }
}