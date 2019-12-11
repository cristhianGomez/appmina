package com.forward.appgestion.data.model

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String
)

data class AuthenticationResponse (
    @SerializedName("token")
    val token: String,
    @SerializedName("codigo_error")
    val codigo_error: String
)
data class LoggedInUser(
    val userId: String,
    val displayName: String,
    val token: String
)