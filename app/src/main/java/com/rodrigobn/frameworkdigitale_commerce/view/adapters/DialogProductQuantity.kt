package com.rodrigobn.frameworkdigitale_commerce.view.adapters

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.bumptech.glide.Glide
import com.rodrigobn.frameworkdigitale_commerce.R
import com.rodrigobn.frameworkdigitale_commerce.data.models.Product
import kotlinx.android.synthetic.main.dialog_quantity.*

class DialogProductQuantity(val product: Product, context: Context): Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_quantity)
        this.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setupCard()
        btnAddProduct.setOnClickListener {
            dismiss()
        }
    }

    private fun setupCard() {
        val product: Product = product

        Glide.with(context)
            .load(product.image)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .into(imgProduct)

    }

    /**
     * Callback for dialog custom
     */
    interface CallbackDialog {
        fun onClickDialogConfirm(product: Product)

    }
}