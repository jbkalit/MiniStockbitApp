package com.jbkalit.data.di

import com.jbkalit.data.BuildConfig.*
import com.jbkalit.data.error.ErrorHandlerImpl
import com.jbkalit.data.factory.RetrofitFactory
import com.jbkalit.data.factory.ScarletFactory
import com.jbkalit.domain.model.error.ErrorHandler
import org.koin.dsl.module

val networkModule = module {

    factory<ErrorHandler> { ErrorHandlerImpl() }

    single {
        RetrofitFactory.create(BASE_URL, API_KEY)
    }

    single {
        ScarletFactory.create(BASE_URL_WEBSOCKET, API_KEY)
    }

}
