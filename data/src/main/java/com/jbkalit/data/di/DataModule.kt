package com.jbkalit.data.di

import com.jbkalit.data.mapper.WebSocketMapper
import com.jbkalit.domain.repository.CryptoRepository
import com.jbkalit.data.repository.CryptoRepositoryImpl
import com.jbkalit.data.service.CryptoService
import com.jbkalit.data.websocket.service.CryptoWebSocketService
import com.jbkalit.domain.usecase.CryptoUseCase
import com.jbkalit.domain.usecase.CryptoUseCaseImpl
import com.jbkalit.domain.usecase.CryptoWebSocketUseCaseImpl
import com.tinder.scarlet.Scarlet
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {

    single { get<Retrofit>().create(CryptoService::class.java) }

    single { get<Scarlet>().create(CryptoWebSocketService::class.java) }

    factory<CryptoUseCase> { CryptoUseCaseImpl(get(), get()) }

    single<CryptoRepository> {
        CryptoRepositoryImpl(get(), get(), get(), get())
    }

    single {
        WebSocketMapper()
    }

}
