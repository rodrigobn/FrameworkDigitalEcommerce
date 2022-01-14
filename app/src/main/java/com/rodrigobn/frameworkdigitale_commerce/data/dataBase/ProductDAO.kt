package com.rodrigobn.frameworkdigitale_commerce.data.dataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rodrigobn.frameworkdigitale_commerce.data.models.Product

@Dao
interface ProductDAO {

    @Query("SELECT * FROM product_table ORDER BY name ASC")
    fun getProducts(): LiveData<List<Product>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(product: Product)

    @Query("DELETE FROM product_table")
    fun deleteAll()
}