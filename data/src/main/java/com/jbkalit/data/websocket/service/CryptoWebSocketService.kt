package com.jbkalit.data.websocket.service

import com.jbkalit.data.websocket.Subscription
import com.jbkalit.data.websocket.response.CryptoWebSocketResponse
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import kotlinx.coroutines.flow.Flow

interface CryptoWebSocketService {

    @Send
    fun subscribe(request: Subscription): Boolean

    @Receive
    fun observeResponse(): Flow<CryptoWebSocketResponse>

    @Receive
    fun observeConnection(): Flow<CryptoWebSocketResponse>

}
