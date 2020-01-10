package com.forward.appgestion.data.network

import com.forward.appgestion.data.model.SpecificRegister.SpecificRegisterList
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object SpecificRegisterService {
    private  val logginInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()

    private val okHttp = OkHttpClient.Builder().addInterceptor(logginInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))

    fun makeSpecificRegisterListService(): SpecificRegisterApi{
        return Retrofit.Builder()
            .baseUrl(ConstanstUrls.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttp.build())
            .build().create(SpecificRegisterApi::class.java)

    }

}