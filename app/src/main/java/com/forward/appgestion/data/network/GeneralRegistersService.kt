package com.forward.appgestion.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object GeneralRegistersService {

    fun makeGeneralRegistersService(): GeneralRegistersApi{
        return Retrofit.Builder()
            .baseUrl(ConstanstUrls.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(GeneralRegistersApi::class.java)

    }
}