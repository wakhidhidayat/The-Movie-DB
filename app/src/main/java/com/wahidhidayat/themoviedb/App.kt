package com.wahidhidayat.themoviedb

import android.app.Application
import com.wahidhidayat.themoviedb.di.presenterModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                listOf(
                    presenterModules
                )
            )
            androidContext(this@App)
        }
    }
}