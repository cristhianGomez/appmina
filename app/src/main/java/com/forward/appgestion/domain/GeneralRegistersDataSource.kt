package com.forward.appgestion.domain

import com.forward.appgestion.data.model.GeneralRegistersListModel
import com.forward.appgestion.data.network.GeneralRegistersService
import retrofit2.Response

class GeneralRegistersDataSource {

    suspend fun getSpecificRegisterData(token: String): Response<GeneralRegistersListModel> {
        return GeneralRegistersService.makeGeneralRegistersService().getGeneralRegistersList(token)
    }

}