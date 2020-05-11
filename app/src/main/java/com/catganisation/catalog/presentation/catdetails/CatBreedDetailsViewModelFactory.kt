package com.catganisation.catalog.presentation.catdetails

import com.catganisation.catalog.presentation.common.BaseViewModelFactory
import javax.inject.Inject
import javax.inject.Provider

class CatBreedDetailsViewModelFactory @Inject constructor(
    override val viewModelProvider: Provider<CatBreedDetailsViewModel>
) : BaseViewModelFactory<CatBreedDetailsViewModel>() {

    override val viewModelClass = CatBreedDetailsViewModel::class
}