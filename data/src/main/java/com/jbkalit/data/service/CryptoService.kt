package com.jbkalit.data.service

import com.jbkalit.data.constant.Constants.QUERY_PARAM_LIMIT
import com.jbkalit.data.constant.Constants.QUERY_PARAM_PAGE
import com.jbkalit.data.constant.Constants.QUERY_PARAM_TSYM
import com.jbkalit.data.model.Cryptos
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoService {

    @GET("data/top/totaltoptiervolfull")
    suspend fun getCryptoData(@Query(QUERY_PARAM_LIMIT) limit: Int,
                              @Query(QUERY_PARAM_PAGE) pageNum: Int,
                              @Query(QUERY_PARAM_TSYM) tsym: String): Cryptos

}
