package com.forward.appgestion.data.repository

import android.app.Application
import android.content.Context
import com.forward.appgestion.data.model.SpecificRegister.SpecificRegisterList
import com.forward.appgestion.domain.SpecificRegisterDataSource


class SpecificRegisterRepository(val dataSource: SpecificRegisterDataSource) {

    var currentMachine: Int? =  null

    private var specificRegisterList: SpecificRegisterList? = null

    suspend fun getSpecificRegister(application: Application,machine:Int): SpecificRegisterList? {
        val sharedPref = application.getSharedPreferences("credentials", Context.MODE_PRIVATE)
        val token = sharedPref.getString("token", null)

        val response = dataSource.getSpecificRegisterData("Bearer "+token!!,machine)
        if (response.isSuccessful) {
            specificRegisterList = response.body()

        } else{
            return null
        }
        return specificRegisterList
    }
}