package com.rodrigobn.frameworkdigitale_commerce.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rodrigobn.frameworkdigitale_commerce.data.dataBase.DatabaseRepository
import com.rodrigobn.frameworkdigitale_commerce.data.dataBase.ProductDAO
import com.rodrigobn.frameworkdigitale_commerce.data.models.Product
import com.rodrigobn.frameworkdigitale_commerce.prefs
import kotlinx.coroutines.launch

class MainViewModel(private val databaseRepository: DatabaseRepository): ViewModel(){

    private val _productList: LiveData<List<Product>> = databaseRepository.getProducts()
    val productListMock: MutableList<Product> = databaseRepository.listOfProductsMock

    val productList: LiveData<List<Product>>
        get() = _productList

    fun save(newProduct: Product){
        viewModelScope.launch {
            databaseRepository.save(newProduct)
        }
    }

    fun logout() {
        prefs.nameProfile = null
        viewModelScope.launch {
            databaseRepository.deleteAll()
        }
    }

    fun deleteAll(){
        viewModelScope.launch {
            databaseRepository.deleteAll()
        }
    }
}