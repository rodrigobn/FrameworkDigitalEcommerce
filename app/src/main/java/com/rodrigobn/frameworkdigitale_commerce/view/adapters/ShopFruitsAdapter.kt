package com.rodrigobn.frameworkdigitale_commerce.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rodrigobn.frameworkdigitale_commerce.R
import com.rodrigobn.frameworkdigitale_commerce.data.models.Product
import kotlinx.android.synthetic.main.item_shop_product.view.*

class ShopFruitsAdapter(
    private val products: MutableList<Product>,
    private val buttonAddProductClickListener: ButtonAddProductClickListener
) : RecyclerView.Adapter<ShopFruitsAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_shop_product, parent, false)
        )
    }

    override fun getItemCount() = products.count()

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindView(products[position], buttonAddProductClickListener)
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
        private val buttonAdd = itemView.buttonAddProduct

        fun bindView(product: Product, buttonAddClickListener: ButtonAddProductClickListener) {

            Glide.with(itemView.context)
                .load(product.image)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(imageProduct)

            titleProduct.text = product.name
            descriptionProduct.text = product.description
            priceProduct.text = "1kg R$ %.2f".format(product.price)

            buttonAdd.setOnClickListener {
                buttonAddClickListener.onButtonAddProductClickListener(product)
            }
        }
    }

    interface ButtonAddProductClickListener {
        fun onButtonAddProductClickListener(product: Product)
    }
}