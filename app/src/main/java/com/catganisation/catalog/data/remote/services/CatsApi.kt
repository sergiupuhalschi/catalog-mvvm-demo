package com.catganisation.catalog.data.remote.services

import com.catganisation.catalog.data.models.CatBreed
import com.catganisation.catalog.data.models.Image
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CatsApi {

    @GET("v1/breeds")
    fun getCatBreeds(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Single<List<CatBreed>>

    @GET("v1/images/search")
    fun getBreedImages(
        @Query("breed_id") breedId: String,
        @Query("page") page: Int = 0,
        @Query("limit") limit: Int = 1,
        @Query("size") size: String = "small"
    ): Single<List<Image>>
}