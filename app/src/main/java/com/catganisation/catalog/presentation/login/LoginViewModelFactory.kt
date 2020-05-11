package com.catganisation.catalog.presentation.login

import com.catganisation.catalog.presentation.common.BaseViewModelFactory
import javax.inject.Inject
import javax.inject.Provider

class LoginViewModelFactory @Inject constructor(
    override val viewModelProvider: Provider<LoginViewModel>
): BaseViewModelFactory<LoginViewModel>() {

    override val viewModelClass = LoginViewModel::class
}