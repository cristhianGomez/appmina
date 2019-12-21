package com.forward.appgestion.data.repository

import android.app.Application
import android.content.Context
import com.forward.appgestion.data.model.GeneralRegistersListModel
import com.forward.appgestion.data.model.SpecificRegister.SpecificRegisterList
import com.forward.appgestion.domain.GeneralRegistersDataSource

class GeneralRegistersRepository(private val dataSource: GeneralRegistersDataSource) {

    var currentMachine: Int? =  null

    private var generalRegistersListModel: GeneralRegistersListModel? = null

    suspend fun getGeneralRegisters(application: Application): GeneralRegistersListModel? {
        val sharedPref = application.getSharedPreferences("credentials", Context.MODE_PRIVATE)
        val token = sharedPref.getString("token", null)

        val response = dataSource.getSpecificRegisterData("Bearer "+token!!)
        if (response.isSuccessful) {
            generalRegistersListModel = response.body()
            println("response.body: $generalRegistersListModel")

        } else{
            return null
        }
        return generalRegistersListModel
    }
}