package com.example.pingoceantesttask.data.api.auth

import com.example.pingoceantesttask.ui.login.LoginRequest
import com.example.pingoceantesttask.ui.login.LoginResult
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("auth")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResult>

}