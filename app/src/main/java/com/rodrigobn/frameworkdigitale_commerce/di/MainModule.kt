package com.rodrigobn.frameworkdigitale_commerce.di

import com.rodrigobn.frameworkdigitale_commerce.view.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {
    viewModel { MainViewModel(get()) }
}