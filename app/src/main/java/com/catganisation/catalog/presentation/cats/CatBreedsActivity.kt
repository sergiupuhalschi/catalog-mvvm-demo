package com.catganisation.catalog.presentation.cats

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.catganisation.catalog.R
import com.catganisation.catalog.databinding.CatBreedsActivityBinding
import com.catganisation.catalog.presentation.catdetails.CatBreedDetailsActivity
import com.catganisation.catalog.presentation.common.ViewModelActivity
import com.catganisation.catalog.presentation.login.LoginActivity
import com.catganisation.catalog.utils.observeNonNull
import com.paginate.Paginate
import javax.inject.Inject

class CatBreedsActivity : ViewModelActivity() {

    @Inject
    lateinit var viewModelFactory: CatBreedsViewModelFactory

    private val viewModel: CatBreedsViewModel by viewModels { viewModelFactory }

    private lateinit var binding: CatBreedsActivityBinding
    private val adapter by lazy { CatBreedsAdapter() }

    private var paginate: Paginate? = null

    companion object {

        fun newIntent(context: Context): Intent =
            Intent(context, CatBreedsActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.cat_breeds_activity)
        binding.viewModel = this@CatBreedsActivity.viewModel
        binding.lifecycleOwner = this@CatBreedsActivity

        observeData()
        initViews()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                viewModel.logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        paginate?.unbind()
    }

    private fun observeData() {
        viewModel.catBreedViewDataList.observeNonNull(this, {
            adapter.updateList(it)
        })
        viewModel.error.observeNonNull(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
        viewModel.openDetails.observeNonNull(this, {
            startActivity(CatBreedDetailsActivity.newIntent(this, it))
        })
        viewModel.openLogin.observeNonNull(this, {
            startActivity(LoginActivity.newIntent(this))
        })
    }

    private fun initViews() {
        binding.catsRV.adapter = adapter
        paginate = Paginate.with(binding.catsRV, viewModel)
            .build()
    }
}