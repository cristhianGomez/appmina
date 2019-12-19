package com.forward.appgestion.data.repository

import android.app.Application
import android.util.Log
import com.forward.appgestion.domain.Result
import com.forward.appgestion.data.model.LoggedInUser
import com.forward.appgestion.domain.LoginDataSource
import java.io.IOException

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource) {

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout(application: Application): Result<String>{
        user = null
        if (dataSource.logout(application)){
            return Result.Success("Logout")
        }
        return Result.Error(IOException())
    }

    suspend fun login(username: String, password: String): Result<LoggedInUser> {
        // handle login

        val response = dataSource.login(username, password)
        if (response.isSuccessful) {
            Log.d("cuack","response: ${response.body()!!}")
            val result = response.body()!!
            val userLogged  = LoggedInUser(result.data!!.user!!.id!!.toString(), result.data!!.user!!.name!!, result.meta!!.token!!)
            setLoggedInUser(userLogged)

            return Result.Success(userLogged)
        }

        return Result.Error(IOException("Error logging in"))
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}
