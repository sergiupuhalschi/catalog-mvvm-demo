package com.catganisation.catalog.presentation.cats

import com.catganisation.catalog.domain.models.CatBreedDto

data class CatBreedViewData(
    val catBreed: CatBreedDto,
    private val onClicked: (CatBreedDto) -> Unit
) {

    fun onCatBreedClicked() {
        onClicked(catBreed)
    }
}