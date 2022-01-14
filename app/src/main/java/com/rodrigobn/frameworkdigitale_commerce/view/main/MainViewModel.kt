package com.rodrigobn.frameworkdigitale_commerce.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rodrigobn.frameworkdigitale_commerce.data.dataBase.ProductDAO
import com.rodrigobn.frameworkdigitale_commerce.data.models.Product

class MainViewModel(private val productDAO: ProductDAO): ViewModel(){

    fun getAllProducts(): LiveData<List<Product>> = productDAO.getProducts()


    fun addProduct(product: Product) {
        productDAO.insert(product)
    }

    fun deleteAllProducts() {
        productDAO.deleteAll()

    }

}