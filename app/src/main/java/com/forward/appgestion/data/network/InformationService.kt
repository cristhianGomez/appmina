package com.forward.appgestion.data.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object InformationService {

    fun makeInformationService(): InformationApi{
        return Retrofit.Builder()
            .baseUrl(ConstanstUrls.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(InformationApi::class.java)

    }
}