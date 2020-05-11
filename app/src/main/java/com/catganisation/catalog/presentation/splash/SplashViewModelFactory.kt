package com.catganisation.catalog.presentation.splash

import com.catganisation.catalog.presentation.common.BaseViewModelFactory
import javax.inject.Inject
import javax.inject.Provider

class SplashViewModelFactory @Inject constructor(
    override val viewModelProvider: Provider<SplashViewModel>
) : BaseViewModelFactory<SplashViewModel>() {

    override val viewModelClass = SplashViewModel::class
}