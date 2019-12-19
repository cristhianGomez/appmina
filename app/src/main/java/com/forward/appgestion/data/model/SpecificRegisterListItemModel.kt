package com.forward.appgestion.data.model


import com.google.gson.annotations.SerializedName

data class SpecificRegisterListItemModel(
    val descripcion: String?,
    @SerializedName("fecha_mantenimiento")
    val fechaMantenimiento: String?,
    @SerializedName("fecha_prox_mantenimiento")
    val fechaProxMantenimiento: String?,
    val id: Int?,
    @SerializedName("user_id")
    val userId: Int?,
    @SerializedName("user_name")
    val userName: String?
)
