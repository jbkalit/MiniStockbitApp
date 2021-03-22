package com.jbkalit.domain.model.state

import com.jbkalit.domain.model.error.ErrorEntity

sealed class State<out T> {
    data class Success<out T>(val data: T) : State<T>()
    data class Error(val errorEntity: ErrorEntity) : State<Nothing>()
    object Loading : State<Nothing>()
}