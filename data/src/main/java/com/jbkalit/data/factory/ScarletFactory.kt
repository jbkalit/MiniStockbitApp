package com.jbkalit.data.factory

import com.jbkalit.data.factory.interceptor.ApiKeyInterceptor
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.messageadapter.gson.GsonMessageAdapter
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object ScarletFactory {

    fun create(baseUrl: String, apiKey: String): Scarlet {
        return Scarlet.Builder()
            .webSocketFactory(httpClient((apiKey))
                .newWebSocketFactory(baseUrl))
            .addMessageAdapterFactory(GsonMessageAdapter.Factory())
            .addStreamAdapterFactory(FlowStreamAdapter.Factory)
            .build()
    }

    private fun httpClient(apiKey: String): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                apiKeyInterceptor(
                    apiKey
                )
            )
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    private fun apiKeyInterceptor(apiKey: String): Interceptor {
        return ApiKeyInterceptor(apiKey)
    }

}