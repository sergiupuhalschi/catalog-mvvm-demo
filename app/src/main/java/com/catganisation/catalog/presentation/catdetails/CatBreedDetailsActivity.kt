package com.catganisation.catalog.presentation.catdetails

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.catganisation.catalog.R
import com.catganisation.catalog.databinding.CatBreedDetailsActivityBinding
import com.catganisation.catalog.domain.models.CatBreed
import com.catganisation.catalog.presentation.common.ViewModelActivity
import com.catganisation.catalog.utils.observeNonNull
import javax.inject.Inject

private const val IN_CAT_BREED_KEY = "IN_CAT_BREED_KEY"

class CatBreedDetailsActivity : ViewModelActivity() {

    @Inject
    lateinit var viewModelFactory: CatBreedDetailsViewModelFactory
    private val viewModel: CatBreedDetailsViewModel by viewModels { viewModelFactory }

    companion object {

        fun newIntent(
            context: Context,
            catBreed: CatBreed
        ) = Intent(context, CatBreedDetailsActivity::class.java).apply {
            putExtra(IN_CAT_BREED_KEY, catBreed)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<CatBreedDetailsActivityBinding>(
            this,
            R.layout.cat_breed_details_activity
        ).apply {
            viewModel = this@CatBreedDetailsActivity.viewModel
            lifecycleOwner = this@CatBreedDetailsActivity
        }

        val catBreed = intent.getParcelableExtra<CatBreed>(IN_CAT_BREED_KEY)
            ?: throw IllegalArgumentException("CatBreed argument is required")
        viewModel.setCatBreed(catBreed)

        initViews()
        observeData()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initViews() {
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }
    }

    private fun observeData() {
        viewModel.name.observeNonNull(this, {
            supportActionBar?.title = it
        })
        viewModel.openUrl.observeNonNull(this, { url ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                Toast.makeText(this, getString(R.string.unexpectedError), Toast.LENGTH_SHORT).show()
            }
        })
    }
}