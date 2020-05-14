package com.catganisation.catalog.presentation.cats

import com.catganisation.catalog.domain.models.CatBreed

data class CatBreedViewData(
    val catBreed: CatBreed,
    private val onClicked: (CatBreed) -> Unit
) {

    fun onCatBreedClicked() {
        onClicked(catBreed)
    }
}