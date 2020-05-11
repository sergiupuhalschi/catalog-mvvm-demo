package com.catganisation.catalog.data.models

import com.google.gson.annotations.SerializedName

data class CatBreed(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("temperament")
    val temperament: String? = null,
    @SerializedName("country_code")
    val countryCode: String? = null,
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("wikipedia_url")
    val wikipediaUrl: String? = null
)