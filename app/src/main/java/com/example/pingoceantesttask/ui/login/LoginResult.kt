package com.example.pingoceantesttask.ui.login

import com.google.gson.annotations.SerializedName

data class LoginResult(
    val token: String?
)

data class User(
    val id: String?,
    val name: String?,
    val email: String?,
    val phone: String?,
    val avatar: String?,
    val position: String?,
    @SerializedName("company_name")
    val companyName: String?,
    @SerializedName("timezone")
    val timeZone: String?
)
