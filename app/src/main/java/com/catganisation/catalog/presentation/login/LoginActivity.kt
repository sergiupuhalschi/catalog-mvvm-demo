package com.catganisation.catalog.presentation.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.catganisation.catalog.R
import com.catganisation.catalog.databinding.LoginActivityBinding
import com.catganisation.catalog.presentation.cats.CatBreedsActivity
import com.catganisation.catalog.presentation.common.ViewModelActivity
import com.catganisation.catalog.utils.observeNonNull
import javax.inject.Inject

class LoginActivity : ViewModelActivity() {

    @Inject
    lateinit var viewModelFactory: LoginViewModelFactory
    private val viewModel: LoginViewModel by viewModels { viewModelFactory }

    private lateinit var binding: LoginActivityBinding

    companion object {

        fun newIntent(context: Context) =
            Intent(context, LoginActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.login_activity)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        observeData()
    }

    private fun observeData() {
        viewModel.openHome.observeNonNull(this, {
            startActivity(CatBreedsActivity.newIntent(this))
        })
        viewModel.error.observeNonNull(this, {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }
}