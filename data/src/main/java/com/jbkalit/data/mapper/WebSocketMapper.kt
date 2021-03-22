package com.jbkalit.data.mapper

import com.jbkalit.data.websocket.response.CryptoWebSocketResponse
import com.jbkalit.domain.model.WebSocket

class WebSocketMapper : Mapper<CryptoWebSocketResponse, WebSocket> {
    override fun map(response: CryptoWebSocketResponse): WebSocket {
        return WebSocket(
            response.type,
            response.symbol,
            response.topTierFullVolume
        )
    }
}
