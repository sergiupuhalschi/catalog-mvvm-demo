package com.catganisation.catalog.data.models

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("width")
    val width: Int? = null,
    @SerializedName("height")
    val height: Int? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("breeds")
    val breeds: List<CatBreed>? = null
)