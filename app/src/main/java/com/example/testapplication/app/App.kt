package com.example.testapplication.app

import android.app.Application
import com.example.testapplication.data.di.dataModule
import com.example.testapplication.domain.di.domainModule
import com.example.testapplication.presentation.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {

val context = this
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(appModule, domainModule, dataModule)
        }
    }

}