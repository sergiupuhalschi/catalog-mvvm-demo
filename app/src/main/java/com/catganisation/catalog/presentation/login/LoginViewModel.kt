package com.catganisation.catalog.presentation.login

import androidx.lifecycle.MutableLiveData
import com.catganisation.catalog.R
import com.catganisation.catalog.domain.models.holders.LoginData
import com.catganisation.catalog.domain.usecases.account.LoginUseCase
import com.catganisation.catalog.presentation.common.BaseViewModel
import com.catganisation.catalog.utils.SingleLiveEvent
import com.catganisation.catalog.utils.providers.StringProvider
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.BiFunction
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val stringProvider: StringProvider
) : BaseViewModel() {

    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    val error = SingleLiveEvent<String>()
    val isLoading = MutableLiveData(false)

    val openHome = SingleLiveEvent<Unit>()

    fun login() {
        getUsername().zipWith(getPassword(),
            BiFunction { username: String, password: String ->
                LoginData(
                    username = username,
                    password = password
                )
            })
            .flatMap { loginUseCase.perform(it) }
            .doFinally { isLoading.value = false }
            .safeSubscribe({
                openHome.value = Unit
            }, {
                error.value = it.message
            })
    }

    private fun getUsername() = Single.just(username.value?.trim() ?: "")
        .map {
            if (it.isEmpty())
                throw IllegalArgumentException(stringProvider.getString(R.string.username_empty_error))
            //TODO add more validation
            it
        }

    private fun getPassword() = Single.just(password.value?.trim() ?: "")
        .map {
            if (it.isEmpty())
                throw IllegalArgumentException(stringProvider.getString(R.string.password_empty_error))
            //TODO add more validation
            it
        }
}