package com.example.pingoceantesttask.data.api.auth

import com.example.pingoceantesttask.ui.login.LoginRequest
import com.example.pingoceantesttask.ui.login.LoginResult
import retrofit2.Response
import javax.inject.Inject

interface AuthApiHelper {
    suspend fun login(loginRequest: LoginRequest): Response<LoginResult>
}

class AuthApiHelperImpl @Inject constructor(
    private val authService: AuthService
): AuthApiHelper {

    override suspend fun login(loginRequest: LoginRequest): Response<LoginResult> {
        return authService.login(loginRequest)
    }

}