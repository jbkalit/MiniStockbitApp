package com.jbkalit.domain.usecase

import com.jbkalit.domain.model.Crypto
import com.jbkalit.domain.model.request.CryptoRequest

interface CryptoUseCase : FlowUseCase<CryptoRequest, List<Crypto>>
