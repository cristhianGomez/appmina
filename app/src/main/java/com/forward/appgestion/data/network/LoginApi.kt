package com.forward.appgestion.data.network

import com.forward.appgestion.data.model.Login
import com.forward.appgestion.data.model.Login2
import com.forward.appgestion.data.model.LoginResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("api/auth/login")
    suspend fun loginWithCredentials(@Body Login: Login): Response<Login2>
}