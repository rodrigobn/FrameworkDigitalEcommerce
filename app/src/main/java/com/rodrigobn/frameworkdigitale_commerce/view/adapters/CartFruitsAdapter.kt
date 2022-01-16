package com.rodrigobn.frameworkdigitale_commerce.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rodrigobn.frameworkdigitale_commerce.R
import com.rodrigobn.frameworkdigitale_commerce.data.models.Product
import kotlinx.android.synthetic.main.item_cart_product.view.*
import kotlinx.android.synthetic.main.item_shop_product.view.descriptionProduct
import kotlinx.android.synthetic.main.item_shop_product.view.imgProduct
import kotlinx.android.synthetic.main.item_shop_product.view.priceProduct
import kotlinx.android.synthetic.main.item_shop_product.view.titleProduct

class CartFruitsAdapter(private val products: MutableList<Product>,
                        private val buttonProductClickListener: CartFruitsAdapter.ButtonProductClickListener
) : RecyclerView.Adapter<CartFruitsAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartFruitsAdapter.ProductViewHolder {
        return CartFruitsAdapter.ProductViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_cart_product, parent, false)
        )
    }

    override fun getItemCount() = products.count()

    override fun onBindViewHolder(holder: CartFruitsAdapter.ProductViewHolder, position: Int) {
        holder.bindView(products[position], buttonProductClickListener)
    }

    fun updateList(itemList: MutableList<Product>) {
        this.products.clear()
        this.products.addAll(itemList)
        notifyDataSetChanged()
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageProduct = itemView.imgProduct
        private val titleProduct = itemView.titleProduct
        private val descriptionProduct = itemView.descriptionProduct
        private val priceProduct = itemView.priceProduct
        private val buttonRemove = itemView.buttonRemoveProduct
        private val buttonUpdate = itemView.buttonUpdateProduct

        fun bindView(
            product: Product,
            buttonProductClickListener: ButtonProductClickListener
        ) {
            Glide.with(itemView.context)
                .load(product.image)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(imageProduct)

            titleProduct.text = product.name
            descriptionProduct.text = product.description
            var total = 0f
            total += product.price * product.quantity
            priceProduct.text = "%skg R$ %.2f".format(product.quantity, total)

            buttonRemove.setOnClickListener {
                buttonProductClickListener.onButtonRemoveProductClickListener(product)
            }

            buttonUpdate.setOnClickListener {
                buttonProductClickListener.onButtonUpdateProductClickListener(product)
            }

        }
    }

    interface ButtonProductClickListener {
        fun onButtonRemoveProductClickListener(product: Product)
        fun onButtonUpdateProductClickListener(product: Product)
    }
}