package com.forward.appgestion.data.network


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object LoginService {
    val logginInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
        HttpLoggingInterceptor.Level.BODY)
    val okHttp = OkHttpClient.Builder().addInterceptor(logginInterceptor)

    fun makeLoginService(): LoginApi {
        return Retrofit.Builder()
            .baseUrl(ConstanstUrls.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create()).client(okHttp.build())
            .build().create(LoginApi::class.java)

    }
}