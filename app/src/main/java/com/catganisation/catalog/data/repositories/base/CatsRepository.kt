package com.catganisation.catalog.data.repositories.base

import com.catganisation.catalog.data.models.CatBreed
import com.catganisation.catalog.data.models.Image
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface CatsRepository {

    fun getBreeds(page: Int, limit: Int): Single<List<CatBreed>>

    fun getBreedImages(breedId: String): Single<List<Image>>
}