package com.catganisation.catalog.data.remote.services

import com.catganisation.catalog.data.models.requests.LoginRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("v1/users/login")
    fun login(@Body request: LoginRequest)
}