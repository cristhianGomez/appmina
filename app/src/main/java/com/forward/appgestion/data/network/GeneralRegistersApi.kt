package com.forward.appgestion.data.network

import com.forward.appgestion.data.model.GeneralRegistersListModel
import com.forward.appgestion.data.model.SpecificRegister.SpecificRegisterList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface GeneralRegistersApi {
    @GET("/api/generic-register")
    suspend fun getGeneralRegistersList(
        @Header("Authorization") authorization: String
    ):Response<GeneralRegistersListModel>

}