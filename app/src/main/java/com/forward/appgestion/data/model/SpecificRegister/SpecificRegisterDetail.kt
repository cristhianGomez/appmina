package com.forward.appgestion.data.model.SpecificRegister


import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
data class SpecificRegisterDetail(
    val register: register?
)
data class register(
    val descripcion: String?,
    @field:Json(name="fecha_mantenimiento")
    val fechaMantenimiento: String?,
    @field:Json(name="fecha_prox_mantenimiento")
    val fechaProxMantenimiento: String?,
    val id: Int?,
    @field:Json(name="imagen_antes")
    val imagenAntes: String?,
    @field:Json(name="imagen_despues")
    val imagenDespues: String?,
    @field:Json(name="machine_id")
    val machineId: Int?,
    val observaciones: String?,
    val sugerencias: String?,
    val tag: String?,
    val tipo: Any?,
    @field:Json(name="trabajos_realizados")
    val trabajosRealizados: String?,
    @field:Json(name="user_id")
    val userId: Int?
)