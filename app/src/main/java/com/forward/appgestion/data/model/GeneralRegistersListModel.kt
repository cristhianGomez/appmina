package com.forward.appgestion.data.model

import com.squareup.moshi.Json

data class GeneralRegistersListModel(
    val data: List<DataGeneralModel>?
)
data class DataGeneralModel(
    @field:Json(name="id")
    val id: String?,
    @field:Json(name="user_name")
    val userName: String?,
    @field:Json(name="nro_orden")
    val orderNumber: String?,
    @field:Json(name="area_trabajo")
    val workArea: String?,
    @field:Json(name="tipo_area")
    val typeArea: String?,
    @field:Json(name="lider_equipo")
    val teamLeader: String?,
    @field:Json(name="registro_lider")
    val registerLeader: String?,
    @field:Json(name="fecha_mant")
    val maintenanceDate: String?,
    @field:Json(name="user_id")
    val userId: String?

)