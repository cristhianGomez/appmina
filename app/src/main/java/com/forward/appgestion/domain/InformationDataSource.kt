package com.forward.appgestion.domain

import android.util.Log
import com.forward.appgestion.data.model.InformationModel
import com.forward.appgestion.data.network.InformationService
import com.squareup.moshi.Json
import retrofit2.Response

class InformationDataSource {

    suspend fun getInformationList(token: String): Response<InformationModel> {
        Log.d("response.a","start getDatasource")
        return InformationService.makeInformationService().getInformationList(token)
    }

}