package com.forward.appgestion.data.network

import com.forward.appgestion.data.model.SpecificRegister.SpecificRegisterList
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object SpecificRegisterService {
    private  val logginInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
        HttpLoggingInterceptor.Level.BODY)
    private val okHttp = OkHttpClient.Builder().addInterceptor(logginInterceptor)

    fun makeSpecificRegisterListService(): SpecificRegisterApi{
        return Retrofit.Builder()
            .baseUrl(ConstanstUrls.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create()).client(okHttp.build())
            .build().create(SpecificRegisterApi::class.java)

    }
}