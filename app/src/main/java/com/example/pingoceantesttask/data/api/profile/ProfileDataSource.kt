package com.example.pingoceantesttask.data.api.profile

import com.example.pingoceantesttask.data.api.BaseDataSource
import com.example.pingoceantesttask.data.model.Result
import com.example.pingoceantesttask.ui.login.User
import retrofit2.Retrofit
import javax.inject.Inject

interface ProfileDataSource {
    suspend fun getProfile(token: String): Result<User>
}

class ProfileDataSourceImpl @Inject constructor(
    private val profileApiHelper: ProfileApiHelper,
    retrofit: Retrofit
): ProfileDataSource, BaseDataSource(retrofit) {

    override suspend fun getProfile(token: String): Result<User> {
        return getResponse(
            request = { profileApiHelper.getProfile(token) },
            defaultErrorMessage = "Error"
        )
    }

}