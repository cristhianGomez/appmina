package com.forward.appgestion.data.model.SpecificRegister


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import java.util.*

data class SpecificRegisterList(
    val `data`: List<Data>?
)

data class Data(
    val descripcion: String?,
    @Json(name="fecha_mantenimiento")
    val fecha_mantenimiento: String?,
    @SerializedName("fecha_prox_mantenimiento")
    val fecha_prox_mantenimiento: String?,
    val id: Int?,
    @SerializedName("user_id")
    val userId: Int?,
    @SerializedName("user_name")
    val userName: String?
)