package com.forward.appgestion.data.network

import com.forward.appgestion.data.model.SpecificRegister.SpecificRegisterDetail
import com.forward.appgestion.data.model.SpecificRegister.SpecificRegisterList
import retrofit2.Response
import retrofit2.http.*

interface SpecificRegisterApi {
        @GET("/api/specific-register/machine/{machine}")
        suspend fun getSpecificRegisterList(@Header("Authorization") authorization: String, @Path("machine") id: Int): Response<SpecificRegisterList>

        @GET("/api/specific-register/{id}")
        suspend fun getSpecificRegisterDetail(@Header("Authorization") authorization: String, @Path("id") id: Int): Response<SpecificRegisterDetail>

//        @POST("/api/specific-register/{id}")
//        suspend fun createSpecificRegisterDetail(@Header("Authorization") authorization: String, @Path("id") id: Int,@Body Login: lOGIN): Response<SpecificRegisterDetail>

}