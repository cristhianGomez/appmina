package com.forward.appgestion.data.network

import com.forward.appgestion.data.model.SpecificRegister.SpecificRegisterList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface SpecificRegisterApi {

        @GET("https://mecanico-pat.herokuapp.com/api/specific-register/machine/{machine}")
        suspend fun getSpecificRegisterList(@Header("Authorization") authorization: String, @Path("machine") id: Int): Response<SpecificRegisterList>
}