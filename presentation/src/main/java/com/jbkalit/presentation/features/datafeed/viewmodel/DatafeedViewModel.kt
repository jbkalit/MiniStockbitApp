package com.jbkalit.presentation.features.datafeed.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.jbkalit.domain.model.WebSocket
import com.jbkalit.domain.usecase.CryptoWebSocketUseCaseImpl
import kotlinx.coroutines.flow.collect

class DatafeedViewModel(
    private val webSocketUseCaseImpl: CryptoWebSocketUseCaseImpl
) : ViewModel() {

    val webSocketData: LiveData<WebSocket> = liveData {
        webSocketUseCaseImpl.execute().collect { data ->
            emit(data)
        }
    }

}
