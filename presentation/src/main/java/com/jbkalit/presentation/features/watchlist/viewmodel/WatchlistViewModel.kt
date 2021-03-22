package com.jbkalit.presentation.features.watchlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jbkalit.domain.model.Crypto
import com.jbkalit.domain.model.request.CryptoRequest
import com.jbkalit.domain.model.state.State
import com.jbkalit.domain.usecase.CryptoUseCase
import com.jbkalit.presentation.event.Event
import com.jbkalit.presentation.extension.cancelIfActive
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WatchlistViewModel(private val cryptoUseCase: CryptoUseCase) : ViewModel() {

    private val items: ArrayList<Crypto>? = null
    private var cryptoJob: Job? = null

    var page = 0

    private val _crypto = MutableLiveData<List<Crypto>>()
    val crypto: LiveData<List<Crypto>>
        get() = _crypto

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<Event<String>>()
    val error: LiveData<Event<String>>
        get() = _error

    fun fetchCryptos(page: Int) {
        cryptoJob.cancelIfActive()
        cryptoJob = viewModelScope.launch {
            val request = CryptoRequest(page)
            nextPage()
            cryptoUseCase.execute(request).collect { res ->
                when (res) {
                    State.Loading -> _loading.value = true
                    is State.Success -> {
                        _loading.value = false

                        if (items.isNullOrEmpty()) {
                            _crypto.postValue(res.data)
                        }
                        updateList(res.data)

                    }
                    is State.Error -> {
                        _loading.value = false
                        _error.value = Event("Check your internet")
                    }
                }
            }
        }
    }

    private fun nextPage() {
        page += 1
    }

    private fun updateList(newItems: List<Crypto>) {
        items?.addAll(newItems)
    }


}
