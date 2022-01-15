package com.rodrigobn.frameworkdigitale_commerce.di

import com.rodrigobn.frameworkdigitale_commerce.data.dataBase.DatabaseRepository
import com.rodrigobn.frameworkdigitale_commerce.data.dataBase.ProductRoomDataBase
import com.rodrigobn.frameworkdigitale_commerce.view.main.MainViewModel
import com.rodrigobn.frameworkdigitale_commerce.view.shopCart.CartViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {
    viewModel { MainViewModel(get()) }
    viewModel { CartViewModel(get()) }
}

val repositoryModule = module {
    single { DatabaseRepository(get()) }
}

val daoProduct = module {
    single { ProductRoomDataBase.getInstance(androidContext()).productDao }
}

