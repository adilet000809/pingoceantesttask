package com.example.pingoceantesttask.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pingoceantesttask.data.repository.ProfileRepository
import com.example.pingoceantesttask.data.model.Result
import com.example.pingoceantesttask.ui.login.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val _userProfileResult = MutableLiveData<Result<User>>()
    val userProfileResult: LiveData<Result<User>> = _userProfileResult

    fun getProfile(token: String) {
        viewModelScope.launch {
            profileRepository.getProfile(token).collect {
                if (it.status == Result.Status.SUCCESS) {
                    _userProfileResult.value = Result.success(it.data)
                }
                if (it.status == Result.Status.LOADING) {
                    _userProfileResult.value = Result.loading()
                }
                if (it.status == Result.Status.ERROR) {
                    _userProfileResult.value = Result.error(it.message ?: "Error", it.error)
                }
            }
        }
    }

}