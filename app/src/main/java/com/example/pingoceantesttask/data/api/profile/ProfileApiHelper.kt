package com.example.pingoceantesttask.data.api.profile

import com.example.pingoceantesttask.ui.login.User
import retrofit2.Response
import javax.inject.Inject

interface ProfileApiHelper {
    suspend fun getProfile(token: String): Response<User>
}

class ProfileApiHelperImpl @Inject constructor(
    private val profileService: ProfileService
): ProfileApiHelper {

    override suspend fun getProfile(token: String): Response<User> {
        return profileService.getProfile(token)
    }
}