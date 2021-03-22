package com.jbkalit.data.repository

import android.util.Log
import com.jbkalit.data.constant.Constants.WEB_SOCKET_SUBSCRIBE_MODEL
import com.jbkalit.data.mapper.WebSocketMapper
import com.jbkalit.domain.model.Crypto
import com.jbkalit.domain.model.request.CryptoRequest
import com.jbkalit.data.service.CryptoService
import com.jbkalit.data.websocket.service.CryptoWebSocketService
import com.jbkalit.domain.model.error.ErrorHandler
import com.jbkalit.domain.model.state.State
import com.jbkalit.domain.repository.CryptoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class CryptoRepositoryImpl(
    private val service: CryptoService,
    private val errorHandler: ErrorHandler,
    private val websocketMapper: WebSocketMapper,
    private val webSocketService: CryptoWebSocketService
) : CryptoRepository {

    override fun getCryptoList(params: CryptoRequest): Flow<State<List<Crypto>>> {
        return flow {
            emit(State.Loading)

            val apiResponse = service.getCryptoData(
                params.limit,
                params.pageNum,
                params.tsym
            )

            val cryptoResponse = apiResponse.data

            val cryptoList = ArrayList<Crypto>()
            for (crypto in cryptoResponse) {
                cryptoList.add(
                    Crypto(
                        name = crypto.coinInfo.name,
                        fullName = crypto.coinInfo.name,
                        currentPrice = crypto.raw.rawDetail.price,
                        changePrice = crypto.raw.rawDetail.changeHour,
                        changePricePercent = crypto.raw.rawDetail.changePCTHour,
                    )
                )
            }

            emit(State.Success(cryptoList))
        }.catch { e ->
            emit(State.Error(errorHandler.getError(e)))
            e.printStackTrace()
        }

    }

    override suspend fun getWebSocketData() = flow {
        webSocketService.subscribe(WEB_SOCKET_SUBSCRIBE_MODEL)
        webSocketService.observeResponse().collect { response ->
            emit(websocketMapper.map(response))
        }
    }

}
