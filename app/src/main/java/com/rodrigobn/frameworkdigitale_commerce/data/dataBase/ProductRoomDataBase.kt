package com.rodrigobn.frameworkdigitale_commerce.data.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rodrigobn.frameworkdigitale_commerce.R
import com.rodrigobn.frameworkdigitale_commerce.data.models.Product

@Database(
    entities = [Product::class],
    version = 1,
    exportSchema = true
)
abstract class ProductRoomDataBase : RoomDatabase() {

    abstract val productDao: ProductDAO

    companion object {
        @Volatile
        private var INSTANCE: ProductRoomDataBase? = null

        fun getInstance(context: Context): ProductRoomDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductRoomDataBase::class.java,
                    "product_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
