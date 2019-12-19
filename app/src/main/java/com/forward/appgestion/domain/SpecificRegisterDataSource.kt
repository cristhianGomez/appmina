package com.forward.appgestion.domain

import android.app.Application
import android.content.Context
import com.forward.appgestion.data.model.*
import com.forward.appgestion.data.model.SpecificRegister.SpecificRegisterList
import com.forward.appgestion.data.network.LoginService
import com.forward.appgestion.data.network.SpecificRegisterService
import retrofit2.Response
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class SpecificRegisterDataSource {

    suspend fun getSpecificRegisterData(token: String, machine: Int): Response<SpecificRegisterList> {
        println("this is token: $token and machine is: $machine ")
        return SpecificRegisterService.makeSpecificRegisterListService().getSpecificRegisterList(token,machine)
    }

}
