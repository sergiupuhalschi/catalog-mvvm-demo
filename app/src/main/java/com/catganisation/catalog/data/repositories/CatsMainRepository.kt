package com.catganisation.catalog.data.repositories

import com.catganisation.catalog.data.models.CatBreed
import com.catganisation.catalog.data.models.Image
import com.catganisation.catalog.data.remote.services.CatsApi
import com.catganisation.catalog.data.repositories.base.CatsRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CatsMainRepository @Inject constructor(
    private val api: CatsApi
) : CatsRepository {

    override fun getBreeds(page: Int, limit: Int): Single<List<CatBreed>>
            = api.getCatBreeds(page, limit)

    override fun getBreedImages(breedId: String): Single<List<Image>>
            = api.getBreedImages(breedId)
}