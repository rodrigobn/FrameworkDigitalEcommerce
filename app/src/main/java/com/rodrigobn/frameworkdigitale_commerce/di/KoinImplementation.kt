package com.rodrigobn.frameworkdigitale_commerce.di

import android.app.Application
import androidx.room.Room
import com.rodrigobn.frameworkdigitale_commerce.data.dataBase.ProductDAO
import com.rodrigobn.frameworkdigitale_commerce.data.dataBase.ProductRoomDataBase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val productDB = module {
    fun provideDataBase(application: Application): ProductRoomDataBase {
        return Room.databaseBuilder(application, ProductRoomDataBase::class.java, "productDB")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideDao(dataBase: ProductRoomDataBase): ProductDAO {
        return dataBase.productDao()
    }
    single { provideDataBase(androidApplication()) }
    single { provideDao(get()) }

}