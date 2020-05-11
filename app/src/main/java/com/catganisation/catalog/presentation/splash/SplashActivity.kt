package com.catganisation.catalog.presentation.splash

import android.os.Bundle
import androidx.activity.viewModels
import com.catganisation.catalog.presentation.cats.CatBreedsActivity
import com.catganisation.catalog.presentation.common.ViewModelActivity
import com.catganisation.catalog.presentation.login.LoginActivity
import com.catganisation.catalog.utils.observeNonNull
import javax.inject.Inject

class SplashActivity : ViewModelActivity() {

    @Inject
    lateinit var viewModelFactory: SplashViewModelFactory
    private val viewModel: SplashViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeState()
    }

    private fun observeState() {
        viewModel.openLogin.observeNonNull(this, {
            startActivity(LoginActivity.newIntent(this))
        })
        viewModel.openHome.observeNonNull(this, {
            startActivity(CatBreedsActivity.newIntent(this))
        })
    }
}