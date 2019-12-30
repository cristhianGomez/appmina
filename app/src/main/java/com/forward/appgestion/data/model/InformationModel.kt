package com.forward.appgestion.data.model

import com.squareup.moshi.Json

data class InformationModel(
//    @field:Json(name="directories")
//    val directories: Any,
    @field:Json(name="files")
    val files: Any
)