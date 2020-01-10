package com.forward.appgestion.data.network

import com.forward.appgestion.data.model.InformationModel
import com.squareup.moshi.Json
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface InformationApi {
    @GET("/api/informacion/directorios")
    suspend fun getInformationList(
        @Header("Authorization") authorization: String
    ): Response<InformationModel>
}