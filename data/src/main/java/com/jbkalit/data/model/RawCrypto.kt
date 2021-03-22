package com.jbkalit.data.model

import com.google.gson.annotations.SerializedName

data class RawCrypto(
    @SerializedName("IDR")
    val rawDetail: RawCryptoDetail = RawCryptoDetail()
)
