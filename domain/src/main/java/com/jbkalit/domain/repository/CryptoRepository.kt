package com.jbkalit.domain.repository

import com.jbkalit.domain.model.Crypto
import com.jbkalit.domain.model.WebSocket
import com.jbkalit.domain.model.request.CryptoRequest
import com.jbkalit.domain.model.state.State
import kotlinx.coroutines.flow.Flow

interface CryptoRepository {
    fun getCryptoList(params: CryptoRequest): Flow<State<List<Crypto>>>
    suspend fun getWebSocketData(): Flow<WebSocket>
}
