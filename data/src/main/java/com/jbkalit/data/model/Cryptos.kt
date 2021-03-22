package com.jbkalit.data.model

import com.google.gson.annotations.SerializedName
import com.jbkalit.data.model.response.BaseResponse
import com.jbkalit.data.model.response.CryptoResponse

data class Cryptos(
    @SerializedName("Data")
    val data: List<CryptoResponse>
): BaseResponse()
