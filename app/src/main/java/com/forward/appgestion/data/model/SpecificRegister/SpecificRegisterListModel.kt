package com.forward.appgestion.data.model.SpecificRegister

import com.squareup.moshi.Json

data class SpecificRegisterList(
    val data: List<Data>
)
//@JsonClass(generateAdapter = true)
data class Data(
    @field:Json(name="id")
    val id: Int?,
    @field:Json(name="fecha_mantenimiento")
    val maintenanceDate: String?,
    @field:Json(name="fecha_prox_mantenimiento")
    val nextMaintenanceDate: String?,
    @field:Json(name="user_name")
    val userName: String?,
    @field:Json(name="descripcion")
    val description: String?,
    @field:Json(name="user_id")
    val userId: Int?
)