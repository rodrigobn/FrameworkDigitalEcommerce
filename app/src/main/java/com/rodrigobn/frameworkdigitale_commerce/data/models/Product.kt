package com.rodrigobn.frameworkdigitale_commerce.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson

@Entity(tableName = "product_table")
class Product(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    @ColumnInfo
    var name: String,
    @ColumnInfo
    var description: String,
    @ColumnInfo
    var quantity: Int,
    @ColumnInfo
    var price: Float,
    @ColumnInfo
    var image: Int) {

    override fun toString(): String {
        return Gson().toJson(this)
    }
}