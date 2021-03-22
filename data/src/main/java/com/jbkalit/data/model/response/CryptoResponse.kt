package com.jbkalit.data.model.response

import com.google.gson.annotations.SerializedName
import com.jbkalit.data.model.CoinInfo
import com.jbkalit.data.model.DisplayCrypto
import com.jbkalit.data.model.RawCrypto

data class CryptoResponse(
    @SerializedName("CoinInfo")
    val coinInfo: CoinInfo,
    @SerializedName("DISPLAY")
    val display: DisplayCrypto,
    @SerializedName("RAW")
    val raw: RawCrypto
)
