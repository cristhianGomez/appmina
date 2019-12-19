package com.forward.appgestion.domain
import android.app.Application
import android.content.Context
import com.forward.appgestion.data.model.*
import com.forward.appgestion.data.network.LoginService
import retrofit2.Response
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    suspend fun login(username: String, password: String): Response<Login2> {
        return LoginService.makeLoginService().loginWithCredentials(Login(username,password))
    }

    fun logout(application: Application):Boolean {
        // TODO: revoke authentication
        val sharedPref = application.getSharedPreferences("credentials", Context.MODE_PRIVATE)
        val token = sharedPref.getString("token", null)
        if (token != null) {
            val editor = sharedPref.edit()
            editor.remove("token")
            editor.remove("user")
            editor.apply()
            return true
        }
        return false
    }
}

