package com.example.pingoceantesttask.data.api.auth

import com.example.pingoceantesttask.data.api.BaseDataSource
import com.example.pingoceantesttask.data.model.Result
import com.example.pingoceantesttask.ui.login.LoginRequest
import com.example.pingoceantesttask.ui.login.LoginResult
import retrofit2.Retrofit
import javax.inject.Inject

interface AuthDataSource {
    suspend fun login(loginRequest: LoginRequest): Result<LoginResult>
}

class AuthDataSourceImpl @Inject constructor(
    private val authApiHelper: AuthApiHelper,
    retrofit: Retrofit
): AuthDataSource, BaseDataSource(retrofit) {

    override suspend fun login(loginRequest: LoginRequest): Result<LoginResult> {
        return getResponse(
            request = { authApiHelper.login(loginRequest) },
            defaultErrorMessage = "Error"
        )
    }
}