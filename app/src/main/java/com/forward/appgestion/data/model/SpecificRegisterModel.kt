package com.forward.appgestion.data.model


import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class Register(
    val descripcion: String?,
    val fecha_mantenimiento: String?,
    @SerializedName("fecha_prox_mantenimiento")
    val fechaProxMantenimiento: String?,
    val id: Int?,
    @SerializedName("imagen_antes")
    val imagenAntes: String?,
    @SerializedName("imagen_despues")
    val imagenDespues: String?,
    @SerializedName("machine_id")
    val machineId: Int?,
    val observaciones: String?,
    val sugerencias: String?,
    val tag: String?,
    val tipo: Any?,
    @SerializedName("trabajos_realizados")
    val trabajosRealizados: String?,
    @SerializedName("user_id")
    val userId: Int?
)
data class SpecificRegisterModelX(
    val register: Register?
)