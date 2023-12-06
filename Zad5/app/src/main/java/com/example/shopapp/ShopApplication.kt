package com.example.shopapp

import android.app.Application
import com.example.shopapp.di.databaseModule
import com.example.shopapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ShopApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ShopApplication)
            modules(databaseModule, viewModelModule)
        }
    }
}