package com.example.pingoceantesttask.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pingoceantesttask.R
import com.example.pingoceantesttask.data.repository.AuthRepository
import com.example.pingoceantesttask.data.util.FormState
import com.example.pingoceantesttask.data.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _loginForm = MutableLiveData<FormState>()
    val loginFormState: LiveData<FormState> = _loginForm

    private val _loginResult = MutableLiveData<Result<LoginResult>>()
    val loginResult: LiveData<Result<LoginResult>> = _loginResult

    fun login(email: String, password: String) {
        viewModelScope.launch {
            authRepository.login(email, password).collect {
                if (it.status == Result.Status.SUCCESS) {
                    _loginResult.value = Result.success(it.data)
                }
                if (it.status == Result.Status.LOADING) {
                    _loginResult.value = Result.loading()
                }
                if (it.status == Result.Status.ERROR) {
                    _loginResult.value = Result.error(it.message ?: "Error", it.error)
                }
            }
        }
    }

    fun loginDataChanged(email: String, password: String) {
        if (!isEmailValid(email)) {
            _loginForm.value = FormState(emailError = R.string.invalid_email)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = FormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = FormState(isDataValid = true)
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return email.trim().matches(emailPattern.toRegex())
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length >= 8
    }

    companion object {
        private const val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    }
}