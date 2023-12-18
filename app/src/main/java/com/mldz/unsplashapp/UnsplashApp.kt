package com.mldz.unsplashapp

import android.app.Application
import com.mldz.unsplashapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class UnsplashApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@UnsplashApp)
            androidLogger()
            modules(appModule)
        }
    }
}