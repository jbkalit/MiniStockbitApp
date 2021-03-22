package com.jbkalit.domain.model

data class Crypto(
    var name: String,
    var fullName: String,
    var currentPrice: Double,
    var changePrice: Double,
    var changePricePercent: Double
)
