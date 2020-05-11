package com.catganisation.catalog.presentation.splash

import com.catganisation.catalog.domain.usecases.account.IsLoggedInUseCase
import com.catganisation.catalog.presentation.common.BaseViewModel
import com.catganisation.catalog.utils.SingleLiveEvent
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val isLoggedInUseCase: IsLoggedInUseCase
) : BaseViewModel() {

    val openLogin = SingleLiveEvent<Unit>()
    val openHome = SingleLiveEvent<Unit>()

    init {
        invalidateSessionState()
    }

    private fun invalidateSessionState() {
        isLoggedInUseCase.perform()
            .safeSubscribe({ isLoggedIn ->
                if (isLoggedIn) openHome.value = Unit else openLogin.value = Unit
            })
    }
}