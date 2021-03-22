package com.jbkalit.domain.model

data class WebSocket(
    var type: Int? = 0,
    var symbol: String? = "",
    var topTierFullVolume: Double? = 0.0
)