package com.rodrigobn.frameworkdigitale_commerce.view.shopCart

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigobn.frameworkdigitale_commerce.data.dataBase.DatabaseRepository
import com.rodrigobn.frameworkdigitale_commerce.data.models.Product
import kotlinx.coroutines.launch

class CartViewModel(private val databaseRepository: DatabaseRepository): ViewModel() {

    private val _productList: LiveData<List<Product>> = databaseRepository.getProducts()

    val productList: LiveData<List<Product>>
        get() = _productList

    fun delete(product: Product){
        viewModelScope.launch {
            databaseRepository.remove(product)
        }
    }
    fun update(product: Product){
        viewModelScope.launch {
            databaseRepository.update(product)
        }
    }
}