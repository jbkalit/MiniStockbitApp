package com.jbkalit.domain.usecase

import com.jbkalit.domain.repository.CryptoRepository

class CryptoWebSocketUseCaseImpl(private val repository: CryptoRepository)  {
    suspend fun execute() = repository.getWebSocketData()
}
