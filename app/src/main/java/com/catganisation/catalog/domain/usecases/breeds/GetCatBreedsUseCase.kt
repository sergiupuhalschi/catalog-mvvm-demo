package com.catganisation.catalog.domain.usecases.breeds

import com.catganisation.catalog.data.models.Image
import com.catganisation.catalog.domain.models.holders.PageData
import com.catganisation.catalog.data.repositories.base.CatsRepository
import com.catganisation.catalog.domain.models.CatBreed
import com.catganisation.catalog.domain.usecases.SingleUseCase
import com.catganisation.catalog.domain.usecases.applySchedulers
import com.catganisation.catalog.utils.providers.schedulers.SchedulerProvider
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetCatBreedsUseCase @Inject constructor(
    private val repository: CatsRepository,
    private val schedulerProvider: SchedulerProvider
) : SingleUseCase<PageData, List<CatBreed>> {

    override fun perform(params: PageData): Single<List<CatBreed>> {
        return repository.getBreeds(params.page, params.limit)
            .flatMap { Observable.fromIterable(it) }
            .flatMapSingle {
                val breedId = it.id ?: return@flatMapSingle Single.just(listOf<Image>())
                repository.getBreedImages(breedId)
            }
            .filter { it.isNotEmpty() }
            .map { it.first() }
            .toList()
            .map { images -> images.toCatBreeds() }
            .applySchedulers(schedulerProvider)
    }

    private fun List<Image>.toCatBreeds() = mapNotNull {
        val breed = it.breeds?.firstOrNull() ?: return@mapNotNull null
        breed.id ?: return@mapNotNull null
        CatBreed(
            id = breed.id,
            name = breed.name ?: "",
            description = breed.description ?: "",
            imageUrl = it.url ?: "",
            temperament = breed.temperament ?: "",
            countryCode = breed.countryCode ?: "",
            country = breed.country ?: "",
            wikipediaUrl = breed.wikipediaUrl ?: ""
        )
    }
}