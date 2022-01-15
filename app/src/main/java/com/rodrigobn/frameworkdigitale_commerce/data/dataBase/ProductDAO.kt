package com.rodrigobn.frameworkdigitale_commerce.data.dataBase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rodrigobn.frameworkdigitale_commerce.data.models.Product

@Dao
interface ProductDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product: Product)

    @Delete
    suspend fun remove(product: Product)

    @Update
    suspend fun update(product: Product)

    @Query("SELECT * FROM product_table")
    fun getProducts(): LiveData<List<Product>>

    @Query("SELECT * FROM product_table WHERE id = :key")
    fun get(key: Long) : Product

    @Query("DELETE FROM product_table")
    suspend fun deleteAll()
}