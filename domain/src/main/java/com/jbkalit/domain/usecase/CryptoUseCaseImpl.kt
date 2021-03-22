package com.jbkalit.domain.usecase

import com.jbkalit.domain.model.Crypto
import com.jbkalit.domain.model.request.CryptoRequest
import com.jbkalit.domain.repository.CryptoRepository
import com.jbkalit.domain.model.error.ErrorHandler
import com.jbkalit.domain.model.state.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class CryptoUseCaseImpl(private val cryptoRepository: CryptoRepository,
                        private val errorHandler: ErrorHandler) : CryptoUseCase {
    override fun execute(params: CryptoRequest): Flow<State<List<Crypto>>> {
        return cryptoRepository.getCryptoList(params).map { response ->
            when (response) {
                is State.Success -> {
                    val mappedResult = response.data
                    State.Success(mappedResult)
                }
                is State.Error -> State.Error(response.errorEntity)
                is State.Loading -> State.Loading
            }
        }.catch { cause ->
            emit(State.Error(errorHandler.getError(cause)))
        }
    }
}
