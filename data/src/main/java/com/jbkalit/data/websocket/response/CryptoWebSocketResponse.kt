package com.jbkalit.data.websocket.response

import com.google.gson.annotations.SerializedName

data class CryptoWebSocketResponse(
    @SerializedName("TYPE")
    val type: Int,
    @SerializedName("SYMBOL")
    val symbol: String,
    @SerializedName("TOPTIERFULLVOLUME")
    val topTierFullVolume: Double
)
