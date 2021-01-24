package com.example.testandroid

import android.app.Application
import com.example.testandroid.di.appModule
import com.example.testandroid.di.databaseModule
import com.example.testandroid.di.repositoryModule
import com.example.testandroid.di.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule,vmModule, repositoryModule, databaseModule))
        }
    }
}