package com.catganisation.catalog.domain.mappers

import com.catganisation.catalog.data.models.Image
import com.catganisation.catalog.domain.models.CatBreed
import javax.inject.Inject

class CatBreedMapper @Inject constructor() {

    fun toDomain(images: List<Image>): List<CatBreed> = images.mapNotNull { toDomain(it) }

    fun toDomain(image: Image): CatBreed? {
        val breed = image.breeds?.firstOrNull() ?: return null
        breed.id ?: return null
        return CatBreed(
            id = breed.id,
            name = breed.name ?: "",
            description = breed.description ?: "",
            imageUrl = image.url ?: "",
            temperament = breed.temperament ?: "",
            countryCode = breed.countryCode ?: "",
            country = breed.country ?: "",
            wikipediaUrl = breed.wikipediaUrl ?: ""
        )
    }
}