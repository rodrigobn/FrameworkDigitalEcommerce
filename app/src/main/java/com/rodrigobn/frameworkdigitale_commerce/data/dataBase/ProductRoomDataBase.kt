package com.rodrigobn.frameworkdigitale_commerce.data.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rodrigobn.frameworkdigitale_commerce.R
import com.rodrigobn.frameworkdigitale_commerce.data.models.Product

@Database(entities = [Product::class], version = 1)
abstract class ProductRoomDataBase : RoomDatabase() {

    abstract fun productDao(): ProductDAO

    fun populateDatabase(productDao: ProductDAO) {
        productDao.deleteAll()
        var product = Product(1, "Maçã", "Vermelha", 0,3.0f, R.mipmap.ic_apple)
        productDao.insert(product)
        product = Product(2, "Pêra", "Pera-d'água", 0, 2.5f, R.mipmap.ic_pear)
        productDao.insert(product)
        product = Product(3, "Banana", "Banana nanica", 0,4.5f, R.mipmap.ic_banana)
        productDao.insert(product)
        product = Product(4, "Abacaxi ", "IMPERIAL", 0,6.5f, R.mipmap.ic_pineapple)
        productDao.insert(product)
        product = Product(5, "Manga", "Manga rosa", 0,1.5f, R.mipmap.ic_mango)
        productDao.insert(product)
    }
}
