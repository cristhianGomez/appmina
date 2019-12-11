package com.forward.appgestion.data.network

import com.forward.appgestion.data.model.AuthenticationResponse
import com.forward.appgestion.data.model.Login
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("/api/authentication/login/")
    suspend fun loginWithCredentials(@Body Login: Login): Response<AuthenticationResponse>
}