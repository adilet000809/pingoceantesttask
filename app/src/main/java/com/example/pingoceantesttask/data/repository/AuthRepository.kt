package com.example.pingoceantesttask.data.repository

import com.example.pingoceantesttask.data.api.auth.AuthDataSource
import com.example.pingoceantesttask.data.model.Result
import com.example.pingoceantesttask.ui.login.LoginRequest
import com.example.pingoceantesttask.ui.login.LoginResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authDataSource: AuthDataSource,
) {
    suspend fun login(username: String, password: String): Flow<Result<LoginResult>> {
        return flow {
            emit(Result.loading())
            val loginResult = authDataSource.login(LoginRequest(username, password))
            emit(loginResult)
        }.flowOn(Dispatchers.IO)
    }
}