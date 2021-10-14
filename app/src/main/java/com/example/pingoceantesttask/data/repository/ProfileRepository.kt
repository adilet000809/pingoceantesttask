package com.example.pingoceantesttask.data.repository

import com.example.pingoceantesttask.data.api.profile.ProfileDataSource
import com.example.pingoceantesttask.ui.login.User
import com.example.pingoceantesttask.data.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val profileDataSource: ProfileDataSource,
) {
    suspend fun getProfile(token: String): Flow<Result<User>> {
        return flow {
            emit(Result.loading())
            val profileResult = profileDataSource.getProfile(token)
            emit(profileResult)
        }.flowOn(Dispatchers.IO)
    }
}
