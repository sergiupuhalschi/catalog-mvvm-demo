package com.catganisation.catalog.presentation.cats

import com.catganisation.catalog.presentation.common.BaseViewModelFactory
import javax.inject.Inject
import javax.inject.Provider

class CatBreedsViewModelFactory @Inject constructor(
    override val viewModelProvider: Provider<CatBreedsViewModel>
) : BaseViewModelFactory<CatBreedsViewModel>() {

    override val viewModelClass = CatBreedsViewModel::class
}