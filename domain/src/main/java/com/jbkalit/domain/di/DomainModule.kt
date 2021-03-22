package com.jbkalit.domain.di

import com.jbkalit.domain.usecase.CryptoUseCaseImpl
import com.jbkalit.domain.usecase.CryptoWebSocketUseCaseImpl
import org.koin.dsl.module

val domainModule = module {

    single {
        CryptoUseCaseImpl(get(), get())
    }

    single {
        CryptoWebSocketUseCaseImpl(get())
    }

}
