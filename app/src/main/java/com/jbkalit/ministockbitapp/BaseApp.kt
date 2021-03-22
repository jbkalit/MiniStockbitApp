package com.jbkalit.ministockbitapp

import android.app.Application
import com.jbkalit.data.di.dataModule
import com.jbkalit.data.di.networkModule
import com.jbkalit.domain.di.domainModule
import com.jbkalit.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

class BaseApp : Application() {

    private val appComponent: List<Module> = listOf(
        presentationModule,
        networkModule,
        domainModule,
        dataModule,
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
