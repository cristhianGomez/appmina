package com.forward.appgestion.data.repository

import android.app.Application
import android.content.Context
import android.util.Log
import com.forward.appgestion.domain.InformationDataSource

class InformationRepository(private val dataSource: InformationDataSource) {

    private var informationListModel = ArrayList<String>()

    suspend fun getInformation(application: Application): ArrayList<String>? {
        val sharedPref = application.
            getSharedPreferences("credentials", Context.MODE_PRIVATE)
        val token = sharedPref.getString("token", null)


        try {
            val response = dataSource.
                getInformationList("Bearer "+token!!)
            if (response.isSuccessful) {
//                informationListModel it a= response.body()
                val bodyToString = response.body().toString()
                val filterBodyString = bodyToString.replace("S,","S")
                                .replace("O,","O")
                val firstArrayList = filterBodyString.split("=[")
                var filterArray = firstArrayList[1].filterNot { c -> "])".contains(c)}
                filterArray = filterArray.replace("informacion/","")
//                for (filter in filterArray){
//                    if (filter.equals("informacion/"))
//
//                }
                informationListModel = filterArray.split(",") as ArrayList<String>
            } else{
                return null
            }
        }
        catch (e: Exception) {
            Log.i("aea",e.toString())
        }
        return informationListModel
    }
}