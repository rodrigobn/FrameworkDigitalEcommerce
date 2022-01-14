package com.rodrigobn.frameworkdigitale_commerce

import android.app.Application
import com.rodrigobn.frameworkdigitale_commerce.data.preferences.Prefs
import com.rodrigobn.frameworkdigitale_commerce.di.productDB
import com.rodrigobn.frameworkdigitale_commerce.di.viewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


val prefs: Prefs by lazy {
    App.prefs!!
}

class App : Application() {

    companion object {
        var prefs: Prefs? = null
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        prefs = Prefs(applicationContext)

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(viewModel, productDB)
        }
    }
}