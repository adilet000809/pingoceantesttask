package com.example.pingoceantesttask.data.api.profile

import com.example.pingoceantesttask.ui.login.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ProfileService {

    @GET("profile")
    suspend fun getProfile(@Header("Authorization") token: String): Response<User>

}