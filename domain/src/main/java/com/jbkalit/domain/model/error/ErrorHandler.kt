package com.jbkalit.domain.model.error

interface ErrorHandler {
    fun getError(throwable: Throwable): ErrorEntity
    fun getError(statusCode: Int, throwable: Throwable? = null): ErrorEntity
}