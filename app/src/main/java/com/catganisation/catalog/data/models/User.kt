package com.catganisation.catalog.data.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("username")
    val username: String? = null,
    @SerializedName("fullName")
    val fullName: String? = null,
    @SerializedName("sessionKey")
    val sessionKey: String? = null
)