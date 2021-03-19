package com.jbkalit.ministockbitapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

class BaseApp : Application() {

    private val appComponent: List<Module> = listOf(

    )

    override fun onCreate() {
        super.onCreate()

        startKoin {
            if (BuildConfig.DEBUG) androidLogger(Level.DEBUG)
            androidContext(this@BaseApp)
            modules(appComponent)
        }
    }
}
