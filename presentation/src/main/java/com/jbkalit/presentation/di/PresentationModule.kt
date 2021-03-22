package com.jbkalit.presentation.di

import com.jbkalit.presentation.features.datafeed.viewmodel.DatafeedViewModel
import com.jbkalit.presentation.features.login.viewmodel.LoginViewModel
import com.jbkalit.presentation.features.watchlist.viewmodel.WatchlistViewModel
import com.jbkalit.presentation.main.viewmodel.MainActivityViewModel
import com.jbkalit.presentation.pref.PrefManager
import com.jbkalit.presentation.pref.PrefManagerImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        LoginViewModel(get())
    }

    viewModel {
        WatchlistViewModel(get())
    }

    viewModel {
        DatafeedViewModel(get())
    }

    viewModel {
        MainActivityViewModel(get())
    }

    single(named(PREFERENCE_NAME)) { "Pref Name" }

    single<PrefManager> { PrefManagerImpl(get(), get(named(PREFERENCE_NAME))) }

}

const val PREFERENCE_NAME = "preference_name"
