package com.jbkalit.data.model

import com.google.gson.annotations.SerializedName

data class DisplayCrypto(
    @SerializedName("BNB")
    val displayDetail: DisplayCryptoDetail = DisplayCryptoDetail()
)
