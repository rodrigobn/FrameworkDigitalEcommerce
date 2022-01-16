package com.rodrigobn.frameworkdigitale_commerce.data.dataBase

import androidx.lifecycle.LiveData
import com.rodrigobn.frameworkdigitale_commerce.R
import com.rodrigobn.frameworkdigitale_commerce.data.models.Product


class DatabaseRepository(private val productDAO: ProductDAO): ProductDAO {

    val listOfProductsMock = mutableListOf(
        Product(1, "Maçã", "Vermelha", 0f,3.0f, R.mipmap.ic_apple),
        Product(2, "Pêra", "Pera-d'água", 0f, 2.5f, R.mipmap.ic_pear),
        Product(3, "Banana", "Banana nanica", 0f,4.5f, R.mipmap.ic_banana),
        Product(4, "Abacaxi ", "IMPERIAL", 0f,6.5f, R.mipmap.ic_pineapple),
        Product(5, "Manga", "Manga rosa", 0f,1.5f, R.mipmap.ic_mango)
    )

    suspend fun save(newProduct: Product) {
        productDAO.insert(newProduct)
    }

    override suspend fun insert(product: Product) {
        productDAO.insert(product)
    }

    override suspend fun remove(product: Product) {
        productDAO.remove(product)
    }

    override suspend fun update(product: Product){
        productDAO.update(product)
    }

    override fun getProducts(): LiveData<List<Product>> {
        return productDAO.getProducts()
    }

    override fun get(key: Long): Product =
        productDAO.get(key)

    override suspend fun deleteAll() {
        productDAO.deleteAll()
    }
}