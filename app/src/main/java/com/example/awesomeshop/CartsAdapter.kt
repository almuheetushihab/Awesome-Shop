import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.awesomeshop.databinding.AdapterCartsBinding
import com.example.awesomeshop.models.product.ProductsResponseItem

class CartsAdapter(private val totalPriceUpdater: TotalPriceUpdater) : RecyclerView.Adapter<CartsAdapter.ViewHolder>() {
    private var cartList = ArrayList<ProductsResponseItem>()

    class ViewHolder(val binding: AdapterCartsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterCartsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = cartList[position]
        var quantity = item.rating.count
        item.quantity = quantity
        var productPrice = item.price * quantity

        viewHolder.binding.tvProductName.text = item.title
        viewHolder.binding.tvPriceKey.text = "Price :"
        viewHolder.binding.tvPriceValue.text = "${item.price} tk"
        viewHolder.binding.tvQuantityKey.text = "Quantity :"
        viewHolder.binding.tvQuantityValue.text = "$quantity pcs"
        viewHolder.binding.tvTotalKey.text = "Total :"
        viewHolder.binding.tvTotalValue.text = "$productPrice tk"

        viewHolder.binding.btnIncrement.setOnClickListener {
            quantity++
            item.quantity = quantity
            productPrice = item.price * quantity
            viewHolder.binding.tvQuantityValue.text = "$quantity pcs"
            viewHolder.binding.tvTotalValue.text = "$productPrice tk"
            updateTotalCartValue()
        }

        viewHolder.binding.btnDecrement.setOnClickListener {
            if (quantity > 1) {
                quantity--
                item.quantity = quantity
                productPrice = item.price * quantity
                viewHolder.binding.tvQuantityValue.text = "$quantity pcs"
                viewHolder.binding.tvTotalValue.text = "$productPrice tk"
                updateTotalCartValue()
            }
        }
    }

    private fun updateTotalCartValue() {
        val totalPrice = cartList.sumByDouble { it.price * it.quantity }
        val formattedTotalPrice = String.format("%.3f", totalPrice).toDouble()
        totalPriceUpdater.updateTotalPrice(formattedTotalPrice)
    }


    override fun getItemCount(): Int {
        return cartList.size
    }

    fun setValues(products: List<ProductsResponseItem>) {
        this.cartList = ArrayList(products)
        notifyDataSetChanged()
    }

    interface TotalPriceUpdater {
        fun updateTotalPrice(totalPrice: Double)
    }
}

