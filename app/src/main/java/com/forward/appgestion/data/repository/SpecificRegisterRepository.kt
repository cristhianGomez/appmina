package com.forward.appgestion.data.repository

import android.app.Application
import android.content.Context
import com.forward.appgestion.data.model.SpecificRegister.SpecificRegisterDetail
import com.forward.appgestion.data.model.SpecificRegister.SpecificRegisterList
import com.forward.appgestion.domain.SpecificRegisterDataSource


class SpecificRegisterRepository(val dataSource: SpecificRegisterDataSource) {

    var currentMachine: Int? =  null

    private var specificRegisterList: SpecificRegisterList? = null
    private var specificRegisterDetail: SpecificRegisterDetail? = null

    suspend fun getSpecificRegister(application: Application,machine:Int): SpecificRegisterList? {
        val sharedPref = application.getSharedPreferences("credentials", Context.MODE_PRIVATE)
        val token = sharedPref.getString("token", null)

        val response = dataSource.getSpecificRegisterData("Bearer "+token!!,machine)
        if (response.isSuccessful) {
            specificRegisterList = response.body()
         //   println("response.body: $specificRegisterList")

        } else{
            return null
        }
        return specificRegisterList
    }
    suspend fun getSpecificRegisterDetail(application: Application,id:Int): SpecificRegisterDetail? {
        val sharedPref = application.getSharedPreferences("credentials", Context.MODE_PRIVATE)
        val token = sharedPref.getString("token", null)

        val response = dataSource.getSpecificRegisterDetailData("Bearer "+token!!,id)
        if (response.isSuccessful) {
            specificRegisterDetail = response.body()

        } else{
            return null
        }
        return specificRegisterDetail
    }

}