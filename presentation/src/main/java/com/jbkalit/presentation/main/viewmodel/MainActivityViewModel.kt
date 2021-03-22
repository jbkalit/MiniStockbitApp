package com.jbkalit.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import com.jbkalit.presentation.pref.PrefKey
import com.jbkalit.presentation.pref.PrefManager

class MainActivityViewModel(private val prefManager: PrefManager) : ViewModel() {

    fun logOut() {
        prefManager.removeKey(PrefKey.EMAIL)
        prefManager.removeKey(PrefKey.IS_LOGGED_IN)
    }

}
