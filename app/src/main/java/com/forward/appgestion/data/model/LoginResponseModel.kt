package com.forward.appgestion.data.model


import com.google.gson.annotations.SerializedName

data class LoginResponseModel(
    val `data`: Data?,
    val meta: Meta?
) {
    data class Data(
        val user: User?
    ) {
        data class User(
            @SerializedName("created_at")
            val createdAt: String?,
            val email: String?,
            @SerializedName("email_verified_at")
            val emailVerifiedAt: Any?,
            val id: Int?,
            val name: String?,
            val register: String?,
            @SerializedName("updated_at")
            val updatedAt: String?
        )
    }

    data class Meta(
        val authenticated: Boolean?,
        val token: String?
    )
}