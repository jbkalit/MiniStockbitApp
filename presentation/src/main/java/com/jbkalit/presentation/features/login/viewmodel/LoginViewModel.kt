package com.jbkalit.presentation.features.login.viewmodel

import androidx.lifecycle.ViewModel
import com.jbkalit.presentation.pref.PrefKey
import com.jbkalit.presentation.pref.PrefManager
import com.jbkalit.presentation.util.Constants.MIN_PASSWORD_LENGTH
import com.jbkalit.presentation.util.StringUtils
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine

@ExperimentalCoroutinesApi
class LoginViewModel(private val prefManager: PrefManager) : ViewModel() {

    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    val authValidation: Flow<Boolean> = combine(_email, _password) { email, password ->
        val isEmailValid = !StringUtils.isValidEmailAddress(email)
        val isPasswordValid = (password.length < MIN_PASSWORD_LENGTH)
        return@combine isEmailValid and isPasswordValid
    }

    fun login() {
        prefManager.saveString(PrefKey.EMAIL, _email.value)
        prefManager.saveBoolean(PrefKey.IS_LOGGED_IN, true)
    }

    fun isLoggedIn(): Boolean {
        if (prefManager.getBoolean(PrefKey.IS_LOGGED_IN)) return true
        return false
    }

    fun logout() {
        prefManager.removeKey(PrefKey.EMAIL)
        prefManager.removeKey(PrefKey.IS_LOGGED_IN)
    }

}
